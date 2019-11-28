package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.bussine.ChartFTE;

@Controller
public class IndicadorFTEController {

	@Autowired
	private ChartFTE service;
	
	@GetMapping("/indicador_fte")
	public String index(Model model) {
		model.addAttribute("MothCurrentAutos",service.createMothCurrent("Autos"));
		model.addAttribute("MothCurrentAyE",service.createMothCurrent("AyE"));
		model.addAttribute("MothCurrentDanos",service.createMothCurrent("Daños"));
		model.addAttribute("MothCurrentVida",service.createMothCurrent("Vida"));
		
		model.addAttribute("MothPreviousAutos",service.createMothPrevious("Autos"));
		model.addAttribute("MothPreviousAyE",service.createMothPrevious("AyE"));
		model.addAttribute("MothPreviousDanos",service.createMothPrevious("Daños"));
		model.addAttribute("MothPreviousVida",service.createMothPrevious("Vida"));
		
		return "mapfre/fte/index";
	}
}
