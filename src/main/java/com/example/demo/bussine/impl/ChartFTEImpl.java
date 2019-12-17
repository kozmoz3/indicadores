package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartFTE;
import com.example.demo.model.ChartCombinationModel;
import com.example.demo.service.FTEService;
import com.example.demo.util.JFreeChartUtil;

@Service
public class ChartFTEImpl implements ChartFTE {

	private static final Logger logger = LogManager.getLogger(ChartFTEImpl.class);
	
	@Autowired
	FTEService service;
	
	
	public List<ChartCombinationModel>createMothCurrent(String sector){
		return recorrer(service.getFoliosMesActual(sector));
	}
	
	public List<ChartCombinationModel>createMothPrevious(String sector){
		return recorrer(service.getFoliosMesPasado(sector));
	}
	
	private List<ChartCombinationModel>recorrer(List<Object[]> listMes){
		List<ChartCombinationModel> chart = new ArrayList<ChartCombinationModel>();
		List<Integer> dataCount = new ArrayList<Integer>();
		List<Integer> dataSum = new ArrayList<Integer>();
		List<String> categories = new ArrayList<String>();
		logger.info("Method: recorrer size "+listMes.size());
		if(listMes.size() == 0) {
			dataCount.add(0);
			dataSum.add(0);
			categories.add("no hay datos");
		}
		for(Object[] emision : listMes) {
			logger.info("Method: recorrer add[ data "+Integer.parseInt(emision[0].toString()));
			dataCount.add(Integer.parseInt(emision[0].toString()));
			dataSum.add(Integer.parseInt(emision[1].toString()));
			categories.add(emision[2].toString());
		}
		chart.add(new ChartCombinationModel("", "column", "RED", dataCount, categories));
		chart.add(new ChartCombinationModel("", "column", "BLUE", dataSum, categories));
		return chart;
	}
}
