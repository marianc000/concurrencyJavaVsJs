/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package folder;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.IntFunction;

/**
 *
 * @author mcaikovs
 */
public class Runner {

    long fibonacci(long n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    int average(List<Long> results) {
        return (int) results.stream().mapToLong(l -> l).average().getAsDouble();
    }

    void measure(IntFunction<Long> code) {
        List<Long> results = new LinkedList<>();
        for (int i = 0; i < 15; i++) {
            long start = System.currentTimeMillis();
            long r = code.apply(40);
            long time = System.currentTimeMillis() - start;
            if (i < 5) {
                System.out.print("skipped ");
            } else {
                results.add(time);
            }
            System.out.println(i + " " + r + " in " + time + " ms");
        }
        System.out.println("average: " + average(results));
    }
}
