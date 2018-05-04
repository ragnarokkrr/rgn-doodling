package ragna.c02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transformation {

    public static void main(String[] args) {
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        final List<String> uppercaseNames = new ArrayList<>();

        for(String name: friends) {
            uppercaseNames.add(name.toUpperCase());
        }

        System.out.println(uppercaseNames);

        uppercaseNames.clear();

        friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));
        System.out.println(uppercaseNames);


        uppercaseNames.clear();

        friends.stream()
                .map(name -> name.toUpperCase())
                .forEach(name-> System.out.print(name  + " "));
        System.out.println();

        friends.stream()
                .map(name -> name.length())
                .forEach(count -> System.out.print(count + " "));
        System.out.println();

        friends.stream()
                .map(String::toUpperCase)
                .forEach(name -> System.out.println(name));


    }

}
