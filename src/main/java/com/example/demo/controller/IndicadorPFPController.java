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

import com.example.demo.bussine.ChartPFP;
import com.example.demo.bussine.impl.ChartPFPImpl;
import com.example.demo.model.DateModel;
import com.example.demo.pdf.PFPPdf;
import com.example.demo.xls.NFAReport;
import com.example.demo.xls.PFPReport;

@Controller
public class IndicadorPFPController {

	private static final Logger logger = LogManager.getLogger(IndicadorPFPController.class);

	@Autowired
	private ChartPFP chartPfp;

	@Autowired
	private PFPReport report;

	@Autowired
	private PFPPdf pdfReport;

	@GetMapping("/indicador_pfp")
	public String index(Model model) {
		logger.info("Method: index " + chartPfp.chart().size());
		model.addAttribute("grafica", chartPfp.chart());
		return "mapfre/pfp/index";
	}

	@PostMapping("/indicador_pfp_show")
	public String show(Model model, @ModelAttribute("datesmodel") DateModel datemodel) {
		String dateStart = datemodel.getDateStart();
		String dateFinish = datemodel.getDateFinish();
		model.addAttribute("datesmodel", new DateModel(dateStart, dateFinish));
		return "mapfre/nfa/show";
	}

	@PostMapping("/download/foliosPendientes.xlsx")
	public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {

		ByteArrayInputStream in = report.create();
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=PFP (Promedio de folios pendientes).xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@PostMapping("/download/pfp.pdf")
	public ResponseEntity<InputStreamResource> CreatePdfReport() throws IOException {

		ByteArrayInputStream in = pdfReport.create(chartPfp.chart());
		// return IOUtils.toByteArray(in);
		logger.info("entro reques2 pdf-> " + in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Prom_Folios_Pendientes.pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(in));
	}
}
