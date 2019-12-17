package com.example.demo.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.DateModel;

@Controller
public class IndicadorTPPSAController {

	private static final Logger logger = LogManager.getLogger(IndicadorTPPSAController.class);
	
	@GetMapping("/indicador_tppsa")
	public String index(Model model) {
		model.addAttribute("datesmodel", new DateModel());
		return "mapfre/tppsa/index";
	}
}
