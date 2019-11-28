package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bussine.ChartNFA;
import com.example.demo.model.ChartBarraModel;
import com.example.demo.model.DateModel;
import com.example.demo.model.DetalleCotizacionModel;
import com.example.demo.xls.NFAReport;

@Controller
public class IndicadorNFAController {

	private static final Logger logger = LogManager.getLogger(IndicadorNFAController.class);

	@Autowired
	private ChartNFA chartNfa;

	@Autowired
	private NFAReport report;

	@GetMapping("/indicador_nfa")
	public String index(Model model) {
		model.addAttribute("datesmodel", new DateModel());
		return "mapfre/nfa/index";
	}

	@PostMapping("/indicador_nfa_show")
	public String show(Model model, @Valid DateModel datemodel, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/indicador_nfa";
		}

		String dateStart = datemodel.getDateStart();
		String dateFinish = datemodel.getDateFinish();
		logger.info("Method show param +[ fecha inicio = " + dateStart + " fecha final = " + dateFinish + " ]");
		model.addAttribute("chartNfa", chartNfa.drawChart(dateStart, dateFinish));
		model.addAttribute("datesmodel", new DateModel(dateStart, dateFinish));
		model.addAttribute("detallemodel", new DetalleCotizacionModel(dateStart, dateFinish));
		return "mapfre/nfa/show";
	}

	@PostMapping("/indicador_nfa_store")
	public @ResponseBody List<ChartBarraModel> store(@Valid DetalleCotizacionModel detallemodel,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			logger.error("Error in Method store error = ");
		}
		String dateStart = detallemodel.getDateStart();
		String dateFinish = detallemodel.getDateFinish();
		logger.info("Method store param +[ fecha inicio = " + dateStart + " fecha final = " + dateFinish + " ]");
		return chartNfa.drawChartStatus(detallemodel);
	}

	@PostMapping("/download/foliosAtendidos.xlsx")
	public ResponseEntity<InputStreamResource> excelCustomersReport(@ModelAttribute("datesmodel") DateModel datesmodel)
			throws IOException {
		String dateStart = datesmodel.getDateStart();
		String dateFinish = datesmodel.getDateFinish();
		System.out.println("entro reques2 xls-> " + dateStart + " final " + dateFinish);
		ByteArrayInputStream in = report.create(datesmodel);
		// return IOUtils.toByteArray(in);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Num Folios Atendidos.xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}
}
