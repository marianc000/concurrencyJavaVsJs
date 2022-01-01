package folder;

import static folder.Constants.MIN_N;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MainConcurrentStealCount {

    long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    Queue<String> q = new ConcurrentLinkedQueue<>();

    int c = 0;

    class Fibonacci extends RecursiveTask<Long> {

        final long n;
        String createdByThread;
        int serialNumber;

        Fibonacci(long n) {
            this.n = n;
            this.createdByThread = Thread.currentThread().getName();
            synchronized (this) {
                c++;
                this.serialNumber = c;
            }
        }

        @Override
        protected Long compute() {
            String threadName = Thread.currentThread().getName();
            if (!threadName.equals(this.createdByThread)) {
                q.add(this.n + " #" + this.serialNumber + " " + threadName + "<=" + this.createdByThread);
            }

            if (n < MIN_N) {
                return fibonacci(n);
            }
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            return f2.compute() + f1.join();
        }
    }

    void run() {
        System.out.println(new Fibonacci(40).compute());
        q.forEach(s -> System.out.println(s.replace("ForkJoinPool.commonPool-", "")));
    }

    public static void main(String... args) {

        new MainConcurrentStealCount().run();
    }
}
