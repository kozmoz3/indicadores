package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ChartModel;

@Service
public interface ChartPFP {

	public List<ChartModel> chart();
}
