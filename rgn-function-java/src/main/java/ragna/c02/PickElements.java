package ragna.c02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PickElements {

    public static void main(String[] args) {
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        final List<String> startsWithN = new ArrayList<>();

        for(String name : friends) {
            if (name.startsWith("N")){
                startsWithN.add(name);
            }
        }

        System.out.println(startsWithN);


        final List<String> startsWithN2 =
                friends.stream()
                    .filter(name -> name.startsWith("N"))
                    .collect(Collectors.toList());


    }

}
