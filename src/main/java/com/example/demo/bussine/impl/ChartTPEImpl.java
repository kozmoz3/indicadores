package com.example.demo.bussine.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartTPE;
import com.example.demo.model.ChartBarraPorcentajeModel;
import com.example.demo.service.TPEService;

@Service
public class ChartTPEImpl implements ChartTPE {

	@Autowired
	TPEService service;

	@Override
	public ChartBarraPorcentajeModel create() {
		List<String> categoria = service.sectorAll();
		List<BigDecimal> data = new ArrayList<BigDecimal>();

		for (int i = 0; i < categoria.size(); i++) {
			data.add(service.AvgTiempoActividadBySector(categoria.get(i)));
		}

		return new ChartBarraPorcentajeModel(categoria, "", data);
	}
}
