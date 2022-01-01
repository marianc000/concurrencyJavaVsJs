package folder;

public class Main extends Runner {

    void run() {
        measure(n -> fibonacci(n));
    }

    public static void main(String... args) {
        new Main().run();
    }
}
