package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.DateModel;
import com.example.demo.repository.XxmpfBpmIndEmiDetalleRepository;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.TPEService;

@Service
public class TPEServiceImpl  implements TPEService{
	
	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	private XxmpfBpmIndEmisionRepository repository;
	
	@Autowired
	private XxmpfBpmIndEmiDetalleRepository detalleRepository;
	
	@Override
	public List<String> sectorAll() {
		// TODO Auto-generated method stub
		return repository.distinctSector();
	}
	
	@Override
   public BigDecimal AvgTiempoActividadBySector(String sector) {
	  return null;
		//return detalleRepository.AvgTiempoActividadBySector(sector);
   }

}
