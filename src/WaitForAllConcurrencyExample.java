/**
 * A simple demo of two tasks that happen concurrently. The caller waits
 * for all task to return. The two tasks can do different computation.
 * 
 * For example, the caller can call Facebook API and Google API and wait
 * for results from both with in a given timeout.
 * 
 * @author Moulaali Shaik
 */
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WaitForAllConcurrencyExample {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    
    ParallelTask taskA = new ParallelTask("A", 4);
    ParallelTask taskB = new ParallelTask("B", 6);
    
    List<Future<String>> futureList = 
        executor.invokeAll(ImmutableList.of(taskA, taskB), 5, TimeUnit.SECONDS);
    System.out.println("taskA : isDone : " + futureList.get(0).isDone() + ". Response : " + 
        (futureList.get(0).isCancelled() ? "null" : futureList.get(0).get()));
    System.out.println("taskb : isDone : " + futureList.get(1).isDone() + ". Response : " + 
        (futureList.get(1).isCancelled() ? "null" : futureList.get(1).get()));
    
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