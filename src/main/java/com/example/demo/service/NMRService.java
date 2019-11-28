package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.model.DateModel;

@Service
public interface NMRService {

	List<String> sectorAll();
	
	List<XxmpfBpmIndEmision> allByFechaFin(DateModel dates);
	
	Integer countBySector(List<XxmpfBpmIndEmision> listEmision, String sector);
	
	List<Object []> allNumMovimientosRealizados(DateModel date);
}
