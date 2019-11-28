package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.bussine.ChartTPM;
import com.example.demo.model.DateModel;

/** 4.10	TPM (Tiempo promedio por movimiento en minutos) **/

@Controller
public class IndicadorTPMController {

	@Autowired
	ChartTPM service;
	
	@GetMapping("/indicador_tpm")
	public String index(Model model) {
		model.addAttribute("grafica",service.create());
		return "mapfre/tpm/index";
	}
	
}
