package com.example.demo.bussine.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartTPM;
import com.example.demo.model.ChartBarraPorcentajeModel;
import com.example.demo.service.TPMService;

@Service
public class ChartTpmImpl  implements ChartTPM{

	@Autowired
	TPMService service;
	
	public List<ChartBarraPorcentajeModel> create(){
		List<String> sector = service.sectores();
		List<ChartBarraPorcentajeModel> chart = new ArrayList<ChartBarraPorcentajeModel>();
		List<BigDecimal> data = new ArrayList<BigDecimal>();
		
		for(int i = 0; i<sector.size(); i++) {
		data.add(service.truncTiemoActividadBySector(sector.get(i)));	
		}
		chart.add(new ChartBarraPorcentajeModel(sector, "", data));
		return chart;
	}
}
