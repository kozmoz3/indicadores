package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.CMEService;

@Service
public class CMEServiceImpl implements CMEService{
	
	@Autowired
	XxmpfBpmIndEmisionRepository emisionRepository;

	@Override
	public List<String> sectores() {
		return emisionRepository.distinctSector();
	}
	
	 static final String countUno=" SELECT COUNT(emision)"
             + " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
             + " WHERE emision.idEmision = detalle.idEmisionFK  AND"
             + " detalle.area = 'Emisión' AND "
             + " emision.sector = :sector AND"
             + " detalle.fechaInicio BETWEEN :dateStart AND :dateFinish"
             + " detalle.estatus = 'Pendiente'"
             + "" ;


}
