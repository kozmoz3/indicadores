package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartFTE;
import com.example.demo.model.ChartCombinationModel;
import com.example.demo.service.FTEService;

@Service
public class ChartFTEImpl implements ChartFTE {

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
		for(Object[] emision : listMes) {
			dataCount.add(Integer.parseInt(emision[0].toString()));
			dataSum.add(Integer.parseInt(emision[1].toString()));
			categories.add(emision[2].toString());
		}
		chart.add(new ChartCombinationModel("", "column", "RED", dataCount, categories));
		chart.add(new ChartCombinationModel("", "column", "BLUE", dataSum, categories));
		return chart;
	}
}
