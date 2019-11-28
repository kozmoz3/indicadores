package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.XxmpfBpmIndCotDetalleRepository;
import com.example.demo.repository.XxmpfBpmIndCotizacionRepository;
import com.example.demo.service.XxmpfBpmIndCotDetalleService;

@Service("cotizacionDetalleImpl")
public class XxmpfBpmIndCotDetalleServiceImpl implements XxmpfBpmIndCotDetalleService{

	@Autowired
	private XxmpfBpmIndCotDetalleRepository repository;
	
	@Override
	public List<String> getDistinctAreaByBetween(String dateStart, String dateFinish) {
		return repository.findDistinctAreaByBetween(dateStart, dateFinish);
	}

	@Override
	public Integer countAreaByFechaInicioBetween(String dateStart, String dateFinish, String area) {
       return repository.countAreaByFechaInicioBetween(dateStart, dateFinish, area);
	}

	@Override
	public Integer findAVGTiempoDiasByFechaInicio(String dateStart, String dateFinish, String area) {
		return  repository.findAVGTiempoDiasByFechaInicio(dateStart, dateFinish, area);
	}

}
