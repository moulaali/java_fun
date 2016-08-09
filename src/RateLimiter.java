import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * A Simple rate limiter that can be used in multi-threaded usecases.
 *
 * @author Moulaali Shaik
 */
public class RateLimiter {
  private Semaphore available;
  private ScheduledExecutorService timerService;

  public  RateLimiter(final int rateInSeconds) {
    available = new Semaphore(rateInSeconds);
    timerService = new ScheduledThreadPoolExecutor(1);
    timerService.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        available.release(rateInSeconds - available.availablePermits());
      }
    }, 1, 1, TimeUnit.SECONDS);
  }

  public void allow() {
    try {
      available.acquire();
    } catch (InterruptedException e) {
      return;
    }
  }

   static class Worker implements Runnable {
    private final RateLimiter rateLimiter;

    Worker(RateLimiter rateLimiter) {
      this.rateLimiter = rateLimiter;
    }

    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        rateLimiter.allow();  // wait for goahead from limiter
        System.out.println((new Date().toString()) + " : " + " Thread : " +
            Thread.currentThread().getId() + " : " + " Got permit");
      }
    }
  }

  public static void main(String[] args) {
    RateLimiter rateLimiter = new RateLimiter(2);  // Allow two operations per second
    new Thread(new Worker(rateLimiter)).start();
    new Thread(new Worker(rateLimiter)).start();
  }
}
