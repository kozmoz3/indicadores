package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface WOAEService {
	
	List<Object[]>getFoliosMesActual(String paramSelect,String estatus);
	
	List<Object[]>getFoliosMesActualAnalista(String paramSelect,String estatus);
	
	List<Object[]>getFoliosMesPasado(String paramSelect,String estatus);
	
	List<Object[]> getFoliosMesAnteriorAnalista(String paramSelect,String estatus);
}
