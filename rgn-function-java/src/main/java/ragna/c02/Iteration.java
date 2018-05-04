package ragna.c02;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Iteration {

    public static void main(String[] args) {
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");


        friends.forEach(new Consumer<String>() {
            @Override
            public void accept(final String name) {
                System.out.println(name);
            }
        });

        friends.forEach((final String name) -> System.out.println(name));

        friends.forEach((name)-> System.out.println(name));

        friends.forEach(System.out::println);
    }

}
