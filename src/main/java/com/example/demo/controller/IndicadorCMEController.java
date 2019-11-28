package com.example.demo.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bussine.ChartCME;
import com.example.demo.model.CMEModel;

@Controller
public class IndicadorCMEController {
	
	private static final Logger logger = LogManager.getLogger(IndicadorCMEController.class);

	@Autowired
	private ChartCME service;
	
	@GetMapping("/indicador_cme")
	public String index(Model model) {
		List<String> listSector = service.getSectores();
		logger.info("method index sectores size = "+listSector.size());
		model.addAttribute("sector", listSector);
		model.addAttribute("model",new CMEModel());
		return "mapfre/cme/index";
	}
	
	@PostMapping("/indicador_cme")
	public String show(Model model, CMEModel cmeModel ) {
		logger.info("method show param [ periodo = "+cmeModel.getPeriodo()+" sector = "+cmeModel.getSector());
		model.addAttribute("sector", service.getSectores());
		model.addAttribute("model",cmeModel);
		
		return "mapfre/cme/index";
	}
}
