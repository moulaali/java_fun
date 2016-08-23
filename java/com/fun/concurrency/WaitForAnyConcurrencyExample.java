/**
 * A simple demo of two tasks that happen concurrently. The caller waits
 * for atleast one task to return. The two tasks do the same computation.
 * 
 * For example, the caller can call two copies of BigTables and return
 * immediately if atleast one returns. This can improve latency at the
 * cost of duplicate requests.
 * 
 * @author Moulaali Shaik
 */
import com.google.common.collect.ImmutableList;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitForAnyConcurrencyExample {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    ParallelTask fastWorker = new ParallelTask("fastWorker", 4);
    ParallelTask slowWorker = new ParallelTask("slowWorker", 6);
    
    String response = executor.invokeAny(ImmutableList.of(fastWorker, slowWorker));
    System.out.println("Got response " + response);
    
    executor.shutdown();
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
      Thread.sleep(timeToComplete*1000);
      System.out.println("Hello from task " + name + " . I am done");
      return "response_from_" + name;
    }
  }
}

