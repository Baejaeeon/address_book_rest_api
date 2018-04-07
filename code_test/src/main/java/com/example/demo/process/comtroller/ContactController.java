package com.example.demo.process.comtroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contact")
public class ContactController {
	
	@RequestMapping("/test")
	public String hello() {
		return "Hello, Spring Boot~!";
	}
	
}
