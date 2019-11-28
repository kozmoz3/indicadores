package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public interface TPMService {

	 BigDecimal truncTiemoActividadBySector(String sector) ;
	 
	 List<String> sectores();
}
