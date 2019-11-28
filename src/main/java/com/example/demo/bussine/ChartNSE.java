package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ChartBarraPorcentajeModel;
import com.example.demo.model.DateModel;

@Service
public interface ChartNSE {
	
	List<ChartBarraPorcentajeModel> chartFilterFecha(DateModel datemodel);

}
