package com.example.demo.bussine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartCME;
import com.example.demo.service.TPMService;

@Service
public class ChartCMEImpl implements ChartCME{

	@Override
	public List<String> getSectores() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Autowired
	TPMService service;
	
	@Override
	public List<String> getSectores(){
		return service.sectores();
	}*/
	
}
