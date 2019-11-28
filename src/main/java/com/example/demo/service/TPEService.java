package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface TPEService {
	List<String> sectorAll();
	
	BigDecimal AvgTiempoActividadBySector(String sector);
}
