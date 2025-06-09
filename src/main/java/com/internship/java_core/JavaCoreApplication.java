package com.internship.java_core;

import com.internship.java_core.custom_linked_list.CustomLinkedList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

@SpringBootApplication
public class JavaCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaCoreApplication.class, args);

        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add(0, "I AM");
        list.add(1, "MUSIC");
        System.out.println(list.get(0) + " " + list.get(1));

        Predicate<String> compareWithIAM = s1 -> Objects.equals(s1, "I AM");
        System.out.println(compareWithIAM.test(list.get(0)));

        Predicate<String> isNull = s -> s == null;
        Predicate<String> isNull2 = Objects::isNull;
        System.out.println("\nIs null? " + isNull2.test("SWAMP IZZO") + "\n");

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("I AM");
        linkedList.add("MUSIC");

        Stream<String> twitchStream = linkedList.stream();
        twitchStream.forEach(System.out::println);

    }

}
