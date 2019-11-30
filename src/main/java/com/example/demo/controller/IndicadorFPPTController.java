package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bussine.ChartFPPT;
import com.example.demo.model.DateModel;
import com.example.demo.xls.FPPTReport;

@Controller
public class IndicadorFPPTController {

	@Autowired
	private ChartFPPT chart;
	
	@Autowired
	FPPTReport report;
	
	@GetMapping("/indicador_fppt")
	public String index(Model model) {
		model.addAttribute("grafica", chart.create());
		return "mapfre/fppt/index";
	}
	
	@PostMapping("/download/foliospendientesxtipo.xlsx")
	public ResponseEntity<InputStreamResource> excelCustomersReport()
			throws IOException {

		ByteArrayInputStream in = report.create();
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Folios pendientes por tipo.xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
	
}
