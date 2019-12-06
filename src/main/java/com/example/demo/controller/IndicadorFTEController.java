package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bussine.ChartFTE;
import com.example.demo.model.DateModel;
import com.example.demo.pdf.FTEPdf;

@Controller
public class IndicadorFTEController {
	private static final Logger logger = LogManager.getLogger(IndicadorFTEController.class);

	@Autowired
	private ChartFTE service;
	
	@Autowired
	private FTEPdf pdfReport;
	
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
	
	@PostMapping("/download/fte.pdf")
	public ResponseEntity<InputStreamResource> CreatePdfReport()
			throws IOException {
		
		ByteArrayInputStream in = pdfReport.create();
		// return IOUtils.toByteArray(in);
		logger.info("entro reques2 pdf-> " + in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Efectividad_por_FTP.pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(in));
	}
}
