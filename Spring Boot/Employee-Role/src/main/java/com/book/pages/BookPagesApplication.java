package com.book.pages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import com.book.pages.model.*;

import com.book.pages.repositores.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookPagesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookPagesApplication.class, args);
	}

 
}