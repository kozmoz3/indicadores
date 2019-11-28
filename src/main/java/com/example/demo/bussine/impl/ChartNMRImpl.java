package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartNMR;
import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.model.ChartColumModel;
import com.example.demo.model.DateModel;
import com.example.demo.service.NMRService;

@Service
public class ChartNMRImpl implements ChartNMR{
	
	@Autowired
	private NMRService service;

	@Override
	public List<ChartColumModel> chart(DateModel date) {
		List<ChartColumModel>listChart = new ArrayList<ChartColumModel>();
		List<XxmpfBpmIndEmision> listEmision = service.allByFechaFin(date);
		List<String> listSector = service.sectorAll();
		List<Integer>data = new ArrayList<Integer>();
		
		for(int i = 0; i<listSector.size(); i++) {
		data.add(service.countBySector(listEmision, listSector.get(i)));	
			
		}
		
		listChart.add(new ChartColumModel(listSector, "", data));
		return listChart;
	}

}
