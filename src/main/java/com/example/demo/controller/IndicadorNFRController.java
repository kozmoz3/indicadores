package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bussine.ChartNFR;
import com.example.demo.model.ChartBarraModel;
import com.example.demo.model.DateModel;
import com.example.demo.pdf.NFRPdf;

@Controller
public class IndicadorNFRController {

	private static final Logger logger = LogManager.getLogger(IndicadorNFRController.class);
	
	
	@Autowired
	@Qualifier("chartNFRImpl")
	private ChartNFR charNFRService;
	
	@Autowired
	private NFRPdf pdfReport;
	
	@GetMapping("/indicador_nfr")
	public String index(Model model) {
		model.addAttribute("datesmodel", new DateModel());
		return "mapfre/nfr/index";
	}
	
	@PostMapping("/indicador_nfr_show")
	public String show(Model model, @Valid DateModel datesmodel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.error("Error method show not valid date "+bindingResult);
            return "redirect:/indicador_nfr";
        }		
		String dateStart = datesmodel.getDateStart();
		String dateFinish = datesmodel.getDateFinish();
		logger.info("method show param [ dateStart "+ dateStart +" dateFinish = "+dateFinish+" ]");
		
		model.addAttribute("datesmodel", new DateModel(dateStart, dateFinish));
		model.addAttribute("dataCotizacion", charNFRService.drawChartBarraSimple(dateStart, dateFinish));
		return "mapfre/nfr/show";
	}
	
	@PostMapping("/download/nfr.pdf")
	public ResponseEntity<InputStreamResource> CreatePdfReport(@ModelAttribute("datesmodel") DateModel datesmodel)
			throws IOException {
		String dateStart = datesmodel.getDateStart();
		String dateFinish = datesmodel.getDateFinish();
		System.out.println("entro reques2 xls-> " + dateStart + " final " + dateFinish);
		ByteArrayInputStream in = pdfReport.create(datesmodel, charNFRService.drawChartBarraSimple(dateStart, dateFinish));
		// return IOUtils.toByteArray(in);
		System.out.println("entro reques2 pdf-> " + in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=foliosrecibidos.pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(in));
	}
}
