package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartFPPT;
import com.example.demo.model.ChartColumModel;
import com.example.demo.service.FPPTService;

@Service
public class ChartFPPTImpl implements ChartFPPT{

	@Autowired
	FPPTService service;
	
	@Override
	public List<ChartColumModel> create() {
		List<String> listSector = service.listSector();
		List<Object[]>listEmision = service.allByEstatus();
		List<ChartColumModel>listChart  = new ArrayList<ChartColumModel>();
		List<Integer>data = new ArrayList<Integer>();
		
		for(int i = 0; i<listSector.size(); i++) {
			data.add(service.countBySector(listEmision, listSector.get(i)));
		}
		listChart.add(new ChartColumModel(listSector, "", data));
	 return listChart;
	}

}
