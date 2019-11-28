package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.NFRService;

@Service("nfrServiceImpl")
public class NFRServiceImpl implements NFRService {
	
	private static final Logger logger = LogManager.getLogger(NFRServiceImpl.class);

	@Autowired
	private XxmpfBpmIndEmisionRepository emisionRepository;
	
	@Autowired
	private EmisionServiceImpl emisionService;

	@Override
	public List<String> findSectorByFechaInicio(String dateStart, String dateFinish) {
		// TODO Auto-generated method stub
		return emisionService.findDistincSectorByFechaInicio(dateStart, dateFinish);
	}

	@Override
	public List<XxmpfBpmIndEmision> findAllEmisionByFechaInicio(String dateStart, String dateFinish) {
	 logger.info("Method findAllEmisionByFechaInicio param [ "+dateStart+" - "+dateFinish);
		return emisionService.findAllEmisionByFechaInicio(dateStart, dateFinish);
	}

	@Override
	public Integer countBySectorAndFechaInicioBetween(List<XxmpfBpmIndEmision>  listEmision, String sector) {
		return emisionService.countBySector(listEmision, sector);
	}
	
	

}
