
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A simple demo on how to fallback to a secondary task if primary task is slow.
 *
 * Instead of always invoking seconday task, we only invoke if primary does not
 * respond in some average expected time.
 */
public class FallbackOnSlowWorkers {

  private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(2);
  private static final CompletionService<String> COMPLETION_SERVICE =
      new ExecutorCompletionService<String>(EXECUTOR);

  public static void main(String[] args) {
    System.out.println("Got response : " + getResultsFromTwoSources());
    // Output is going to be :
    // Hello from task workerA . I started my job now
    // Timed out waiting for taskA. Falling back.
    // Hello from task workerB . I started my job now
    // Hello from task workerB . I am done
    // Got response : response_from_workerB
  }

  private static String getResultsFromTwoSources() {
    ParallelTask workerA = new ParallelTask("workerA", 5);
    ParallelTask workerB = new ParallelTask("workerB", 1);

    Future<String> workerAFuture = COMPLETION_SERVICE.submit(workerA);

    try {
      return workerAFuture.get(2, TimeUnit.SECONDS);
    } catch (TimeoutException toe) {
      System.out.println("Timed out waiting for taskA. Falling back.");
      Future<String> workerBFuture = COMPLETION_SERVICE.submit(workerB);
      try {
        String result = COMPLETION_SERVICE.take().get();  // Wait for either one to return
        // Cancel both futures to return the resources
        workerAFuture.cancel(true /** interrupt if running */);
        workerBFuture.cancel(true /** interrupt if running */);
        return result;
      } catch (InterruptedException | ExecutionException e) {
        return null;
      }
    } catch (InterruptedException | ExecutionException e) {
      return null;
    }
  }

  static class ParallelTask implements Callable<String> {
    private final int timeToComplete;
    private final String name;

    public ParallelTask(String name, int timeToComplete) {
      this.timeToComplete = timeToComplete;
      this.name = name;
    }

    @Override
    public String call() throws InterruptedException {
      System.out.println("Hello from task " + name + " . I started my job now");
      Thread.sleep(timeToComplete * 1000);
      System.out.println("Hello from task " + name + " . I am done");
      return "response_from_" + name;
    }
  }
}
