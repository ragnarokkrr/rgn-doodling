package ragna.c02;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PickMultipleCollections {

    public static void main(String[] args) {
        final List<String> friends =
                Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        final List<String> comrades =
                Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");

        final List<String> editors =
                Arrays.asList("Brian", "Jackie", "John", "Mike");


        final long countFriendsStartN =
                friends.stream().filter(name->name.startsWith("N")).count();

        System.out.println("friends: " + countFriendsStartN);

        final long countComradesStartN =
                comrades.stream().filter(name -> name.startsWith("N")).count();

        System.out.println("comrades: " + countComradesStartN);

        final long countEditorsStartN =
                editors.stream().filter(name -> name.startsWith("N")).count();

        System.out.println("editors: " + countEditorsStartN);



        final Predicate<String> startsWithN = name -> name.startsWith("N");


        final long countFriendsStartN2 =
                friends.stream().filter(startsWithN).count();

        System.out.println("friends: " + countFriendsStartN2);

        final long countComradesStartN2 =
                comrades.stream().filter(startsWithN).count();

        System.out.println("comrades: " + countComradesStartN2);

        final long countEditorsStartN2 =
                editors.stream().filter(startsWithN).count();

        System.out.println("editors: " + countEditorsStartN2);

    }

}
