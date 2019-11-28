package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ChartBarraModel;

@Service("chartNFR")
public interface ChartNFR {

	List<ChartBarraModel> drawChartBarraSimple(String dateStart, String dateFinish);
	
}
