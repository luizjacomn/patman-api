package com.luizjacomn.patmanapi;

import org.springframework.boot.SpringApplication;

public class TestPatmanApplication {

	public static void main(String[] args) {
		SpringApplication.from(PatmanApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
