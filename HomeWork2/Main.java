package Lesson2.HomeWork2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long ageLess18 = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> listForArmy = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());


        List<Person> listForWork = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.WOMAN && person.getAge() < 60) || (person.getSex() == Sex.MAN && person.getAge() < 65))
                .filter(person -> person.getEducation() == Education.HIGHER)
                        .sorted(Comparator.comparing(Person::getFamily))
                                .collect(Collectors.toList());



        System.out.println(ageLess18);
        System.out.println(listForArmy);
        System.out.println(listForWork);
    }
}
