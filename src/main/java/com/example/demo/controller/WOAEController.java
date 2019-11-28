
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.bussine.WOADevolucion;


@Controller
public class WOAEController {
	
	@Autowired
	@Qualifier("devolucion")
	private WOADevolucion service;
	

	@GetMapping("/woae")
	public String show(Model model) {
		model.addAttribute("actualDevolucionDivisional",service.mesActual("divisional","Devolución"));
		model.addAttribute("actualDevolucionRegional",service.mesActual("regional","Devolución"));
		model.addAttribute("actualDevolucionAgente",service.mesActual("agente","Devolución"));
		model.addAttribute("actualDevolucionAnalista",service.mesActualAnalista("nombreanalista","Devolución"));
		//
		model.addAttribute("anteriorDevolucionDivisional",service.mesAnterior("divisional","Devolución"));
		model.addAttribute("anteriorDevolucionRegional",service.mesAnterior("regional","Devolución"));
		model.addAttribute("anteriorDevolucionAgente",service.mesAnterior("agente","Devolución"));
		model.addAttribute("anteriorDevolucionAnalista",service.mesAnteriorAnalista("nombreanalista","Devolución"));
		//
		//Rechazo
		model.addAttribute("actualRechazoDivisional",service.mesActual("divisional","Rechazo manual"));
		model.addAttribute("actualRechazoRegional",service.mesActual("regional","Rechazo manual"));
		model.addAttribute("actualRechazoAgente",service.mesActual("agente","Rechazo manual"));
		model.addAttribute("actualRechazoAnalista",service.mesActualAnalista("nombreanalista","Rechazo manual"));
		//
		model.addAttribute("anteriorRechazoDivisional",service.mesAnterior("divisional","Rechazo manual"));
		model.addAttribute("anteriorRechazoRegional",service.mesAnterior("regional","Rechazo manual"));
		model.addAttribute("anteriorRechazoAgente",service.mesAnterior("agente","Rechazo manual"));
		model.addAttribute("anteriorRechazoAnalista",service.mesAnteriorAnalista("nombreanalista","Rechazo manual"));
		//
		//Pendiente
		model.addAttribute("actualPendienteDivisional",service.mesActual("divisional","Pendiente de información"));
		model.addAttribute("actualPendienteRegional",service.mesActual("regional","Pendiente de información"));
		model.addAttribute("actualPendienteAgente",service.mesActual("agente","Pendiente de información"));
		model.addAttribute("actualPendienteAnalista",service.mesActualAnalista("nombreanalista","Pendiente de información"));
		//
		model.addAttribute("anteriorPendienteDivisional",service.mesAnterior("divisional","Pendiente de información"));
		model.addAttribute("anteriorPendienteRegional",service.mesAnterior("regional","Pendiente de información"));
		model.addAttribute("anteriorPendienteAgente",service.mesAnterior("agente","Pendiente de información"));
		model.addAttribute("anteriorPendienteAnalista",service.mesAnteriorAnalista("nombreanalista","Pendiente de información"));
		//
		//Sistemas
		model.addAttribute("actualSistemaDivisional",service.mesActual("divisional","Sistemas"));
		model.addAttribute("actualSistemaRegional",service.mesActual("regional","Sistemas"));
		model.addAttribute("actualSistemaAgente",service.mesActual("agente","Sistemas"));
		model.addAttribute("actualSistemaAnalista",service.mesActualAnalista("nombreanalista","Sistemas"));
		//
		model.addAttribute("anteriorSistemaDivisional",service.mesAnterior("divisional","Sistemas"));
		model.addAttribute("anteriorSistemaRegional",service.mesAnterior("regional","Sistemas"));
		model.addAttribute("anteriorSistemaAgente",service.mesAnterior("agente","Sistemas"));
		model.addAttribute("anteriorSistemaAnalista",service.mesAnteriorAnalista("nombreanalista","Sistemas"));
		return "mapfre/woae/index";
	}
}
