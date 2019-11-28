package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.DateModel;
import com.example.demo.model.FPRModel;

@Service
public interface ChartFPR {

	List<FPRModel> create(DateModel date);
}
