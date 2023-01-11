package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {
	
	@GetMapping("/hello")
	public String message() {
		return "Hello World";
	}
}
