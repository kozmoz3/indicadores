package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.DateModel;

@Controller
public class HolidayController {

	@GetMapping("/admin_holiday")
	public String index(Model model) {
		model.addAttribute("datesmodel", new DateModel());
		return "mapfre/holiday/index";
	}
	
}
