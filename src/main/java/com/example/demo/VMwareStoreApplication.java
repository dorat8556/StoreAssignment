package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = "com.example.demo.jpa.repositories")
@SpringBootApplication
public class VMwareStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(VMwareStoreApplication.class, args);
	}
}
