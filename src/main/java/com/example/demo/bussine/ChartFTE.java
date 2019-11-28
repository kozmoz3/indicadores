package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ChartCombinationModel;

@Service
public interface ChartFTE {

	List<ChartCombinationModel>createMothCurrent(String sector);
	
	List<ChartCombinationModel>createMothPrevious(String sector);
}
