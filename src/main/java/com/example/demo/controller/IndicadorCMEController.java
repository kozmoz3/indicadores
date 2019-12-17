package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.bussine.ChartCME;
import com.example.demo.model.CMEModel;
import com.example.demo.model.DateModel;

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
		String sector = cmeModel.getSector();
		String fecha = cmeModel.getPeriodo();
		logger.info("Method:show param [ periodo = "+cmeModel.getPeriodo()+" sector = "+cmeModel.getSector());
		model.addAttribute("sector", service.getSectores());
		model.addAttribute("model",cmeModel);
		List<Object[]> listNumFolios= service.numFoliosPendientes(sector, fecha);
		
	Integer countFolios = service.countDetalle(listNumFolios);
	String color = service.getColor(countFolios);
	model.addAttribute("countFolios",countFolios );
	model.addAttribute("color",color );
	logger.info("Method:show add[ count = "+countFolios);
		return "mapfre/cme/show";
	}
	
	@PostMapping("/download/cme_foliosPendientes.xlsx")
	public ResponseEntity<InputStreamResource> excelCustomersReport(@ModelAttribute("datesmodel") DateModel datesmodel)
			throws IOException {
		String dateStart = datesmodel.getDateStart();
		String dateFinish = datesmodel.getDateFinish();
		logger.info("entro reques2 xls-> " + dateStart + " final " + dateFinish);
		ByteArrayInputStream in =null;// report.create(datesmodel);
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Num Folios Atendidos.xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
}
