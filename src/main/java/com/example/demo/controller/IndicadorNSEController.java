package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Chart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bussine.ChartNSE;
import com.example.demo.model.ChartBarraDobleModel;
import com.example.demo.model.DateModel;

@Controller
public class IndicadorNSEController {
	
	@Autowired
    ChartNSE chartNSE;

	@GetMapping("/indicador_nse")
	public String index(Model model) {
		model.addAttribute("datesmodel", new DateModel());
		return "mapfre/nse/index";
	}
	
	@PostMapping("/indicador_nse_show")
	public String show(Model model, @Valid DateModel datemodel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "redirect:/indicador_nse";
        }
		String dateStart = datemodel.getDateStart();
		String dateFinish = datemodel.getDateFinish();	
		
		model.addAttribute("datesmodel", new DateModel(dateStart, dateFinish));
		model.addAttribute("grafica", chartNSE.chartFilterFecha(datemodel));
		return "mapfre/nse/show";
	}
}
