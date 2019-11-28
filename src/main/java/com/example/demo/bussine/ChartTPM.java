package com.example.demo.bussine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ChartBarraPorcentajeModel;

@Service
public interface ChartTPM {

	List<ChartBarraPorcentajeModel> create();
}
