package com.example.demo.model;

import java.math.BigDecimal;
import java.util.List;

public class ChartBarraPorcentajeModel {

	private List<String> categoria;
	
	private String name;
	
	private List<BigDecimal>data;

	public ChartBarraPorcentajeModel(List<String> categoria, String name, List<BigDecimal> data) {
		super();
		this.categoria = categoria;
		this.name = name;
		this.data = data;
	}

	public List<String> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<String> categoria) {
		this.categoria = categoria;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BigDecimal> getData() {
		return data;
	}

	public void setData(List<BigDecimal> data) {
		this.data = data;
	}
	
	
}
