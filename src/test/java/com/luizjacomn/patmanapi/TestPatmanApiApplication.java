package com.luizjacomn.patmanapi;

import org.springframework.boot.SpringApplication;

public class TestPatmanApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(PatmanApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
