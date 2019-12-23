package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pdf.NFAPdf;
import com.example.demo.repository.XxmpfBpmIndEmiDetalleRepository;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.TPMService;

@Service
public class TPMServiceImpl implements TPMService {
	
	private static final Logger logger = LogManager.getLogger(TPMServiceImpl.class);

	@Autowired
	XxmpfBpmIndEmisionRepository emisionRepository;

	@Autowired
	XxmpfBpmIndEmiDetalleRepository detalleRepository;

	@Override
	public BigDecimal truncTiemoActividadBySector(String sector) {
		return null;
		//return detalleRepository.truncTiempoActividadBySector(sector);
	}

	@Override
	public List<String> sectores() {
		return emisionRepository.distinctSector();
	}

}
