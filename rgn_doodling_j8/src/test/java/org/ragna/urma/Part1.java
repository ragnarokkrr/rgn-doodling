package org.ragna.urma;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Created by ragnarokkrr on 1/11/16.
 */
public class Part1 {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

    @Test
    public void listing6() {


        final List<Integer> twoEvenSquares = numbers.stream()
                .filter(n -> {
                    System.out.println("filtering " + n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    System.out.println("mapping " + n);
                    return n * n;
                })
                .limit(2)
                .collect(toList());
        System.out.println(twoEvenSquares);

    }

    @Test
    public void listing11() {
        int sum = numbers.stream()
                .reduce(0,
                        (a, b) -> {
                            System.out.printf("%s+%s\n", a, b);
                            return a + b;
                        });
        System.out.println(sum);
    }

    @Test
    public void listing12() {
        int product = numbers.stream()
                .reduce(1, (a, b) -> {
                    System.out.printf("%s * %s\n", a, b);
                    return a * b;
                });
        System.out.println("product " + product);

        int max = numbers.stream().reduce(1, Integer::max);
        System.out.println("max " + max);
    }


    @Test
    public void listing15() {
        IntStream oddNumbers =
                IntStream.rangeClosed(10, 30)
                        .filter(n -> n % 2 == 1);
        oddNumbers.forEach(n -> System.out.println(n));
    }
}
