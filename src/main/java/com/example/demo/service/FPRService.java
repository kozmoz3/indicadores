package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DateModel;

@Service
public interface FPRService {

	List<Object []> allFolioRecibidoByFechaInicio(DateModel date);
	
	public List<Object []> allFolioAtendidoByFechaFin(DateModel date);
	
	List<String> getSectores();
}
