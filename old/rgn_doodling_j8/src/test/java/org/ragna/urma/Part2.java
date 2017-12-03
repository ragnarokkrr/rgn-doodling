package org.ragna.urma;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by ragnarokkrr on 1/11/16.
 */
public class Part2 {

    @Test
    public void listing2() {
        Stream<String> words = Stream.of("Java", "Magazine", "is", "the", "best");

        final Map<String, Long> letterToCount = words.map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .collect(groupingBy(identity(), counting()));
        System.out.println(letterToCount);
    }

    @Test
    public void listing4() throws IOException, URISyntaxException {
        final Path path = Paths.get(this.getClass().getResource("stuff.txt").toURI());
        System.out.println(path.toString());
        Files.lines(path)
                .map(line -> {
                    final String[] split = line.split("\\s+");
                    System.out.println(Arrays.toString(split));
                    return split;
                })
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void listing6() throws URISyntaxException, IOException {
        final Path path = Paths.get(this.getClass().getResource("stuff.txt").toURI());

        Files.lines(path)
                .map(line -> line.split("\\s+"))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
    }
}
