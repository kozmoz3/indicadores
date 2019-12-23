package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartCME;
import com.example.demo.controller.IndicadorNFAController;
import com.example.demo.model.EndosoPoliza;
import com.example.demo.repository.CMERepository;
import com.example.demo.service.CMEService;
import com.example.demo.service.TPMService;
import com.example.demo.util.DateUtil;

@Service
public class ChartCMEImpl implements ChartCME{

	private static final Logger logger = LogManager.getLogger(ChartCMEImpl.class);

	@Autowired
	private TPMService serviceTpm;
	
	@Autowired
	private CMEService service;
	
	@Autowired
	private CMERepository repository;
	
	
	@Override
	public List<String> getSectores(){
		return serviceTpm.sectores();
	}
	
	
	@Override
	public String getColor( int count){
		if(count < 200) {
			return "verde";
		}else if(count >= 200 && count < 300) {
			return "azul";
		}
		return "rojo";
	}
	
	@Override
	public Integer countDetalle(List<Object []> list){
	Long count=	 list.stream().count();
	return count.intValue();
	}


	@Override
	public List<Object[]> numFoliosPendientes(String sector, String fecha) {
		// TODO Auto-generated method stub
		return service.foliosRegistre(sector, fecha);
	}
	
	@Override
	public List<Object[]> excelFoliosPendientes(String sector, String fecha){
		return service.excelFoliosPendientes(sector, fecha);
	}
	
	@Override
	public List<Object[]> excelComplit(String sector, String fecha){
		return service.excelComplit(sector, fecha);
	}
	
	@Override
	public List<EndosoPoliza> EndososPolizas(String sector, String fecha){
		List<Object[]> listResults = service.getPolizasAndEndosos(sector, fecha);
		List<EndosoPoliza> listEndosos = new ArrayList<EndosoPoliza>();
		
		if(listResults.size() == 0) {
			listEndosos.add(new EndosoPoliza(0, ""));
		}
		for (Object[] emision : listResults) {
			int count = Integer.parseInt(emision[0].toString());
			String solicitud = emision[1].toString();
			listEndosos.add(new EndosoPoliza(count, solicitud));
			logger.info("Method: EndososPolizas add[ Count = " + count + ", Solicitud= "+ solicitud );

		}
		return listEndosos;
	}
	
}
