package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;

@Service
public interface NSEService {

	List<String> findAllSectores();
	
	List<XxmpfBpmIndEmision> findAllAtendidos(String dateStart,String dateFinish);
	
	List<XxmpfBpmIndEmision> findAllRecibidos(String dateStart,String dateFinish);
	
	 List<XxmpfBpmIndEmision> findAllAtiempo(String dateStart, String dateFinish);
}
