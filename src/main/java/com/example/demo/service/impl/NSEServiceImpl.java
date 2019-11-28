package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.NSEService;

@Service
public class NSEServiceImpl  implements NSEService{

	@Autowired
	XxmpfBpmIndEmisionRepository emision;
	
	@Override
	public List<String> findAllSectores() {
		return emision.distinctSector();
	}

	@Override
	public List<XxmpfBpmIndEmision> findAllAtendidos(String dateStart, String dateFinish) {
		// TODO Auto-generated method stub
		return emision.findAllByAreaAndFechaFin(dateStart, dateFinish).stream().filter(detalle-> !detalle.getEstatus().equals("Pendiente")).collect(Collectors.toList());
	}

	@Override
	public List<XxmpfBpmIndEmision> findAllRecibidos(String dateStart, String dateFinish) {
		return emision.findAllByAreaAndFechaInicio(dateStart, dateFinish);
	}
	
	@Override
	public List<XxmpfBpmIndEmision> findAllAtiempo(String dateStart, String dateFinish) {
		return emision.findAllByAreaAndFechaFinAndAtiempo(dateStart, dateFinish);
	}

}
