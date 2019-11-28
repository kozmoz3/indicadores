package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface XxmpfBpmIndCotizacionService {
 
	List<String> distinctEstatus();
	
	int countCotizacion();
	
	int countRegistreByEstatus(String status);
	
	List<String> getStatusByBetween(String dateStart,String dateFinish);
	
	int countEstatusByFechaInicioBetweens(String dateStart,String dateFinish,String status );
	
	int countEstatusByFechaInicioBetween(String dateStart,String dateFinish );
	
	int countRecotizacionByFechaInicioBetween(String dateStart,String dateFinish );
	
	int countDevolucionByFechaInicioBetween(String dateStart,String dateFinish );
	
	Integer getAVGTiempoDiasByFecaInicio(String dateStart,String dateFinish);
	
	 List<String> findDistinctMotivoByFechaInicio(String dateStart,String dateFinish, String status);
	 
	 Integer countMotivosByFechaInicioBetween(String dateStart,String dateFinish,String motivo, String status );
	 
	 Integer totalMotivosByFechaInicioBetween(String dateStart,String dateFinish,String status );
	 
	 
}
