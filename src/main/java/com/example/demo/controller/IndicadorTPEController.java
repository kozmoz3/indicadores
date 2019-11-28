package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.bussine.ChartTPE;
import com.example.demo.model.DateModel;

@Controller
public class IndicadorTPEController {

	@Autowired
	ChartTPE service;
	
	@GetMapping("/indicador_tpe")
	public String index(Model model) {
		model.addAttribute("grafica", service.create());
		return "mapfre/tpe/index";
	}
	
}
