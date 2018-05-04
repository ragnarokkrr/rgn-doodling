package ragna.c02;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class PickDifferentNames {

    public static void main(String[] args) {
        final List<String> friends =
                Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        final Predicate<String> startsWithN = name -> name.startsWith("N");
        final Predicate<String> startsWithB = name -> name.startsWith("B");


        final long countFriendsStartsWithN =
                friends.stream().filter(startsWithN).count();

        System.out.println("friends N: " + countFriendsStartsWithN);

        final long countFriendsStartB =
                friends.stream().filter(startsWithB).count();
        System.out.println("friends B: " + countFriendsStartB);


        final long countFriendsN =
                friends.stream().filter(checkIfStartsWith("N")).count();
        System.out.println("friends N:" + countFriendsN);

        final long countFriendsB =
                friends.stream().filter(checkIfStartsWith("B")).count();
        System.out.println("Friends B:" +countFriendsB);



        //narrowing scope
        Function<String, Predicate<String>> startsWithLetter =
                (String letter) -> {
                    Predicate<String> checkStartsWith =
                            (String name) -> name.startsWith(letter);
                    return checkStartsWith;
                };

        //reduce clutter
        Function<String, Predicate<String>> startsWithLetter2 =
                (String letter) -> (String name) -> name.startsWith(letter);

        //remove types
        Function<String, Predicate<String>> startsWithLetter3 =
                letter -> name -> name.startsWith(letter);

        final long countFriendsStartN =
                friends.stream().filter(startsWithLetter3.apply("N")).count();

        System.out.println("Counts N: " + countFriendsStartN);


        final long countFriendsBs =
                friends.stream().filter(startsWithLetter3.apply("B")).count();
        System.out.println("counts b: " + countFriendsBs);

    }

    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }

}
