package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ChartColumModel;
import com.example.demo.model.ChartModel;

@Service
public interface WOADevolucion {

	List<ChartModel> mesActual(String selectParam, String estatus);
	
	List<ChartModel> mesActualAnalista(String selectParam, String estatus);
	
	List<ChartModel> mesAnterior(String selectParam, String estatus);
	
	List<ChartModel> mesAnteriorAnalista(String selectParam, String estatus);
	
}
