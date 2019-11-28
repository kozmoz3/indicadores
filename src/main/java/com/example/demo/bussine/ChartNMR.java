package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ChartColumModel;
import com.example.demo.model.DateModel;

@Service
public interface ChartNMR {

	List<ChartColumModel>chart(DateModel date);
	
}
