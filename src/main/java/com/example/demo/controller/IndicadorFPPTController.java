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

import com.example.demo.bussine.ChartFPPT;
import com.example.demo.model.DateModel;
import com.example.demo.pdf.PFPPdf;
import com.example.demo.xls.FPPTReport;

@Controller
public class IndicadorFPPTController {
	
	private static final Logger logger = LogManager.getLogger(IndicadorFPPTController.class);

	@Autowired
	private ChartFPPT chart;

	@Autowired
	private FPPTReport report;
	
	@Autowired
	private  PFPPdf pdfReport;

	@GetMapping("/indicador_fppt")
	public String index(Model model) {
		model.addAttribute("grafica", chart.create());
		return "mapfre/fppt/index";
	}

	@PostMapping("/download/foliospendientesxtipo.xlsx")
	public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {

		ByteArrayInputStream in = report.create();
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Folios pendientes por tipo.xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@PostMapping("/download/fppt.pdf")
	public ResponseEntity<InputStreamResource> CreatePdfReport() throws IOException {

		ByteArrayInputStream in = pdfReport.create(chart.create());
		// return IOUtils.toByteArray(in);
		logger.info("entro reques2 pdf-> " + in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Num_Folios_Atendidos.pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(in));
	}

}
