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

import com.example.demo.bussine.ChartTPM;
import com.example.demo.model.DateModel;
import com.example.demo.pdf.TPMPdf;

/** 4.10	TPM (Tiempo promedio por movimiento en minutos) **/

@Controller
public class IndicadorTPMController {
	
	private static final Logger logger = LogManager.getLogger(IndicadorTPMController.class);

	@Autowired
	private ChartTPM service;
	
	@Autowired
	private TPMPdf pdfReport;
	
	@GetMapping("/indicador_tpm")
	public String index(Model model) {
		model.addAttribute("grafica",service.create());
		return "mapfre/tpm/index";
	}
	
	@PostMapping("/download/tpm.pdf")
	public ResponseEntity<InputStreamResource> CreatePdfReport()throws IOException {
		
		
		ByteArrayInputStream in = pdfReport.create(service.create());
		// return IOUtils.toByteArray(in);
		logger.info("entro reques2 pdf-> " + in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Tiempo_promedio_movimiento_minuto.pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(in));
	}
	
}
