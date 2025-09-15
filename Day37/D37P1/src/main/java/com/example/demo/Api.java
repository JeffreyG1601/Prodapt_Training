package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {
	@GetMapping("/home")
	public String home() {
		return "<h1> Hi hello bye</h1>";
	}
	@GetMapping("/test")
	public String test() {
		return "<h1> This is not a test</h1>";
	}
}
