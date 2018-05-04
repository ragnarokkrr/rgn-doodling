package ragna.c06;

import java.util.Arrays;
import java.util.List;

public class LazyStreams {
    private static int length(final String name) {
        System.out.println("getting length for " + name);
        return name.length();
    }

    private static String toUpper(final String name) {
        System.out.println("converting to uppercase " + name);
        return name.toUpperCase();
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Brad", "Kate", "Kim", "Jack", "Joe", "Mike", "Susan", "George", "Robert",
                "Julia", "Parker", "Benson");

        final String firstNameWith3letters =
                names.stream()
                .filter(name -> length(name)==3)
                //.map(name ->toUpper(name))
                .map(LazyStreams::toUpper)
                .findFirst()
                .get();
        System.out.println(firstNameWith3letters);
    }

}
