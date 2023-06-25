package com.example.redissonclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class RemoteServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteServiceClientApplication.class, args);
	}
}
