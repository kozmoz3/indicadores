package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.WOADevolucion;
import com.example.demo.model.ChartColumModel;
import com.example.demo.model.ChartModel;
import com.example.demo.service.WOAEService;

@Service("devolucion") 
public class WOADevolucionImpl implements WOADevolucion{
	
	
	private static final Logger logger = LogManager.getLogger(WOADevolucionImpl.class);
	
	@Autowired
	WOAEService service;

	@Override
	public List<ChartModel> mesActual(String selectParam, String estatus) {
		return recorrer( service.getFoliosMesActual(selectParam,estatus));	
	}
	
	@Override
	public List<ChartModel> mesActualAnalista(String selectParam, String estatus){
		return recorrer(service.getFoliosMesActualAnalista(selectParam,estatus));
	}
	
	@Override
	public List<ChartModel> mesAnterior(String selectParam, String estatus) {
		return recorrer( service.getFoliosMesPasado(selectParam,estatus));	
	}
	
	@Override
	public List<ChartModel> mesAnteriorAnalista(String selectParam, String estatus){
		return recorrer(service.getFoliosMesAnteriorAnalista(selectParam,estatus));
	}

	private List<ChartModel> recorrer(List<Object[]> list) {
		List<ChartModel> chart = new ArrayList<ChartModel>();
		int contador = 0;
		for(Object[] emision : list) {
			contador++;
			if(contador <= 5) {
				int y = Integer.parseInt( emision[0].toString());
			
			String name = emision[1].toString();
			
			logger.info("method recorrer add list param [ name = "+name+" countFolios = "+y +" ]");
			chart.add(new ChartModel(contador, name, y));
			}
		}
		
		return chart;
	}
	
}
