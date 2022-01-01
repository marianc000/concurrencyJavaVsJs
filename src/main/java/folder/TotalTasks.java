package folder;

import static folder.Constants.MIN_N;

public class TotalTasks {

    int c=0, c2 = 0;

    long fibonacci(long n) {
        if (n >= MIN_N) {
            c++;
        }
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    void run() {
        c = 0;
        System.out.println(fibonacci(40) + " " + c);
    }

    public static void main(String... args) {
        new TotalTasks().run();
    }
}
