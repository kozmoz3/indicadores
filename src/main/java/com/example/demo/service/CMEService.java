package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CMEService {
	
	public List<String> sectores();
	
	List<Object []> countDetalle(String sector,String fecha);
	
	List<Object []> foliosRegistre(String sector,String fecha);
	
	List<Object []> getPolizasAndEndosos(String sector,String fecha);
	
	List<Object[]> getDetalleFolio(String sector, String fecha);
	
	List<Object[]> detalleFoliosPendientes(String sector, String fecha);
	
	List<Object[]> excelFoliosPendientes(String sector, String fecha);
	
	List<Object[]> excelComplit(String sector, String fecha);
	

}
