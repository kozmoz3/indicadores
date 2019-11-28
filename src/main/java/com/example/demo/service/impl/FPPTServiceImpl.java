package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.EmisionService;
import com.example.demo.service.FPPTService;

@Service
public class FPPTServiceImpl implements FPPTService{

	@Autowired
	XxmpfBpmIndEmisionRepository repository;
	
	@Autowired
	EmisionService emisionService;
	
	@Override
	public List<Object []> allByEstatus(){
		String status = "EN_PROCESO";
		return emisionService.allByEstatus(status);
	}

	@Override
	public Integer countBySector(List<Object[]> listEmision, String sector) {
		// TODO Auto-generated method stub
		int indexSector = 1;
		return emisionService.countSector(listEmision, sector, indexSector);
	}

	@Override
	public List<String> listSector() {
		// TODO Auto-generated method stub
		return repository.distinctSector();
	}

}
