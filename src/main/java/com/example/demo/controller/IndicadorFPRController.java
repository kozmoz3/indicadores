package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bussine.ChartFPR;
import com.example.demo.model.DateModel;
import com.example.demo.model.DetalleCotizacionModel;
import com.example.demo.xls.FPRReport;

@Controller
public class IndicadorFPRController {
	
	private static final Logger logger = LogManager.getLogger(IndicadorFPRController.class);

	@Autowired
	ChartFPR service;
	
	@Autowired
	FPRReport report;
	
	@GetMapping("/indicador_fpr")
	public String index(Model model) {
		model.addAttribute("datesmodel", new DateModel());
		return "mapfre/fpr/index";
	}
	
	@PostMapping("/indicador_fpr_show")
	public String show(Model model, @Valid DateModel datemodel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/indicador_fpr";
		}

		String dateStart = datemodel.getDateStart();
		String dateFinish = datemodel.getDateFinish();
		logger.info("Method show param +[ fecha inicio = " + dateStart + " fecha final = " + dateFinish + " ]");
		
		model.addAttribute("datesmodel", new DateModel(dateStart, dateFinish));
		model.addAttribute("listHorario",service.create(datemodel));
		return "mapfre/fpr/show";
	}
	
	@PostMapping("/download/FoliosRecibidosXHora.xlsx")
	public ResponseEntity<InputStreamResource> excelCustomersReport(@ModelAttribute("datesmodel") DateModel datesmodel)
			throws IOException {
		String dateStart = datesmodel.getDateStart();
		String dateFinish = datesmodel.getDateFinish();
		System.out.println("entro reques2 xls-> " + dateStart + " final " + dateFinish);
		ByteArrayInputStream in = report.create(datesmodel);
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Folios Recibidos por hora.xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
}
