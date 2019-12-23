package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.DateModel;
import com.example.demo.repository.XxmpfBpmIndEmiDetalleRepository;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.TPEService;

@Service
public class TPEServiceImpl  implements TPEService{
	
	private static final Logger logger = LogManager.getLogger(TPEServiceImpl.class);
	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	private XxmpfBpmIndEmisionRepository repository;
	
	@Autowired
	private XxmpfBpmIndEmiDetalleRepository detalleRepository;
	
	@Override
	public List<String> sectorAll() {
		// TODO Auto-generated method stub
		logger.info("Method:sectorAll");
		return repository.distinctSector();
	}
	
	@Override
   public BigDecimal AvgTiempoActividadBySector(String sector) {
		logger.info("Method:AvgTiempoActividadBySector");
		return detalleRepository.AvgTiempoActividadBySector(sector);
   }

}
