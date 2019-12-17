package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChartCME {

	List<String> getSectores();
	
	String getColor( int count);
	
	Integer countDetalle(List<Object []> list);
	
	List<Object []> numFoliosPendientes(String sector, String fecha);
	
	List<Object[]> excelFoliosPendientes(String sector, String fecha);
	
	List<Object[]> excelComplit(String sector, String fecha);
	
}
