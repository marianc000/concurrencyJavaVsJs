package folder;

import static folder.Constants.MIN_N;
import java.util.Deque;
import java.util.LinkedList;

public class MainWorkQueue {

    static long fibonacci(long n) {

        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    static Deque<Fibonacci> workQueue = new LinkedList<>();

    static class Fibonacci {

        final long n;

        Fibonacci(long n) {
            this.n = n;
        }

        void fork() {
            workQueue.push(this);
        }

        Long join() {
            return workQueue.pop().compute();
        }

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
        System.out.println(new Fibonacci(40).compute());
    }

    public static void main(String... args) {

        new MainWorkQueue().run();
    }
}
