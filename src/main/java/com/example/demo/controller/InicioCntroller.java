package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioCntroller {

	@GetMapping("/")
	public String index() {
		System.out.println("--------------------------entro --");
		return "index";
	}
}
