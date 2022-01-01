package folder;

import static folder.Constants.MIN_N;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MainConcurrent extends Runner {

    class Fibonacci extends RecursiveTask<Long> {

        final long n;

        Fibonacci(long n) {
            this.n = n;
        }

        @Override
        protected Long compute() {
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
        measure(n -> new Fibonacci(n).compute());
    }

    public static void main(String... args) {
        System.out.println("threads: " + ForkJoinPool.getCommonPoolParallelism()
                + "; processors: " + Runtime.getRuntime().availableProcessors());
        new MainConcurrent().run();
    }
}
