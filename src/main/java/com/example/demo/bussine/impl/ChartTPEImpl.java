package com.example.demo.bussine.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartTPE;
import com.example.demo.model.ChartBarraPorcentajeModel;
import com.example.demo.service.TPEService;
import com.example.demo.service.impl.TPEServiceImpl;

@Service
public class ChartTPEImpl implements ChartTPE {
	
	private static final Logger logger = LogManager.getLogger(ChartTPEImpl.class);

	@Autowired
	TPEService service;

	@Override
	public ChartBarraPorcentajeModel create() {
		logger.info("Method:create");
		List<String> categoria = service.sectorAll();
		logger.info("Method:create list sectores size = "+categoria.size());
		List<BigDecimal> data = new ArrayList<BigDecimal>();

		for (int i = 0; i < categoria.size(); i++) {
			String sector = categoria.get(i);
			BigDecimal datos =service.AvgTiempoActividadBySector(categoria.get(i));
			logger.info("Method:create add[ sector =  "+categoria.get(i)+", data = "+datos);
			data.add(datos);
		}

		return new ChartBarraPorcentajeModel(categoria, "", data);
	}
}
