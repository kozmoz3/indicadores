package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ChartBarraModel;
import com.example.demo.model.DetalleCotizacionModel;

@Service("chartNFAService")
public interface ChartNFA {

	public List<ChartBarraModel> drawChart(String dateStart, String dateFinish);
	
	public List<ChartBarraModel> drawChartStatus(DetalleCotizacionModel detallemodel);
}
