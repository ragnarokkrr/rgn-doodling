package ragna.c03;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class OlderThan20 {

    public static void main(String[] args) {
        final List<Person> people = Arrays.asList(
                new Person("John" ,20),
                new Person("Sarah", 21),
                new Person("Jane", 21),
                new Person("Greg", 35)
        );


        List<Person> olderThan20 = new ArrayList<>();

        people.stream()
                .filter(person -> person.getAge() > 20)
                .forEach(person -> olderThan20.add(person));
        System.out.println("People older than 20: " + olderThan20);


        List<Person> olderThan20b =
                people.stream()
                .filter(person -> person.getAge() > 20)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("Peopler older than 20b: " + olderThan20b);


        List<Person> olderThan20c =
                people.stream()
                .filter(person -> person.getAge() > 20)
                .collect(Collectors.toList());
        System.out.println("People older than 20c: " + olderThan20c);

        //Grouping by

        Map<Integer, List<Person>> peopleByAge =
                people.stream()
                .collect(groupingBy(Person::getAge));

        System.out.println("People grouped by age: " + peopleByAge);

        //people name
        Map<Integer, List<String>> nameOfPeopleByAge =
                people.stream()
                .collect(groupingBy(Person::getAge, mapping(Person::getName, toList())));

        System.out.println("People grouped by age: " + nameOfPeopleByAge);


        //group by name first char and get the oldest for each group
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonInEachAlphabet =
                people.stream()
                    .collect(groupingBy(person-> person.getName().charAt(0),
                            reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person in each alphabet:");
        System.out.println(oldestPersonInEachAlphabet);
    }

}
