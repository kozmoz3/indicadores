package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ChartColumModel;

@Service
public interface ChartFPPT {

	List<ChartColumModel>create();
}
