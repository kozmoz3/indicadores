package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service("xxmpfBpmIndCotDetalleService")
public interface XxmpfBpmIndCotDetalleService {

	List<String> getDistinctAreaByBetween(String dateStart,String dateFinish );
	
	Integer countAreaByFechaInicioBetween(String dateStart,String dateFinish,String area );
	
	Integer findAVGTiempoDiasByFechaInicio(String dateStart,String dateFinish, String area);
}
