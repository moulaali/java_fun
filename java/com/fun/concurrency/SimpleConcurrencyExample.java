/**
 * A simple demo of two parallel tasks that execute independently.
 * 
 * @author Moulaali Shaik
 */
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimpleConcurrencyExample {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    Future<Boolean> workerA = executor.submit(new ParallelTaskA("1", 4));
    Future<Boolean> workerB = executor.submit(new ParallelTaskA("2", 6));
    
    workerA.get();
    workerB.get();
    
    executor.shutdown();
  }
  
  static class ParallelTaskA implements Callable<Boolean> {
    private final int timeToComplete;
    private final String name;
    
    public ParallelTaskA(String name, int timeToComplete) {
      this.timeToComplete = timeToComplete;
      this.name = name;
    }
    
    @Override
    public Boolean call() throws InterruptedException {
      System.out.println("Hello from task " + name + " . I started my job now");
      Thread.sleep(timeToComplete*1000);
      System.out.println("Hello from task " + name + " . I am done");
      return true;
    }
  }
}

