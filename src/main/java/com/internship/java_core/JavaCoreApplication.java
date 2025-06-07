package com.internship.java_core;

import com.internship.java_core.custom_linked_list.CustomLinkedList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaCoreApplication.class, args);

		CustomLinkedList<String> list = new CustomLinkedList<>();
		list.add(0,"I AM");
		list.add(1,"MUSIC");
		System.out.println(list.get(0) + " " + list.get(1));
	}

}
