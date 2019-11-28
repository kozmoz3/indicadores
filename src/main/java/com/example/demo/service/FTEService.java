package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface FTEService {

	List<Object[]>getFoliosMesActual(String sector);
	
	public List<Object[]>getFoliosMesPasado(String sector);
}
