package ragna.c03;

import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Compare {

    public static void main(String[] args) {

        final List<Person> people = Arrays.asList(
          new Person("John" ,21),
          new Person("Sarah", 21),
          new Person("Jane", 21),
          new Person("Greg", 35)
        );
        
        List<Person> ascendingAge =
                people.stream()
                .sorted((person1, person2) -> person1.ageDifference(person2))
                .collect(Collectors.toList());
        printPeople("Sorted in ascending order of age: ", ascendingAge);


        // simple comparator
        Comparator<Person> compareAscending = (person1, person2) ->
                person1.ageDifference(person2);
        Comparator<Person> compareDescending = compareAscending.reversed();

        printPeople("Sorted in ascending order of age: ",
                people.stream().sorted(compareAscending).collect(Collectors.toList()));

        printPeople("Sorted in descending order of age: ",
                people.stream().sorted(compareDescending).collect(Collectors.toList()));

        people.stream()
                .min(Person::ageDifference)
                .ifPresent(youngest -> System.out.println("Youngest: " + youngest));

        people.stream()
                .max(Person::ageDifference)
                .ifPresent(eldest -> System.out.println("Eldest: " + eldest));

        // multiple fluent comparisons
        final Function<Person, String> byName = person -> person.getName();
        final Function<Person, Integer> byAge = person -> person.getAge();

        //people.stream().sorted(comparing(byName));

        printPeople("Sorted in ascending order of age and name:",
                people.stream()
                    .sorted(comparing(byAge).thenComparing(byName))
                    .collect(Collectors.toList()));
    }

    private static void printPeople(String message, List<Person> people) {
        System.out.println(message);
        people.forEach(System.out::println);
    }

}
