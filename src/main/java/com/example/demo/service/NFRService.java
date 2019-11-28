package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;

@Service("nfrService")
public interface NFRService {

	 List<String> findSectorByFechaInicio(String dateStart,String dateFinish );
	 
	 List<XxmpfBpmIndEmision> findAllEmisionByFechaInicio(String dateStart, String dateFinish);
	 
	 public Integer countBySectorAndFechaInicioBetween(List<XxmpfBpmIndEmision>  listEmision, String sector);
}
