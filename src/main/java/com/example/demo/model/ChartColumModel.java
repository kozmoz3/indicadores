package com.example.demo.model;

import java.util.List;

public class ChartColumModel {

	private List<String> categorias;
	private String name;
	private List<Integer>data;
	
	
	public ChartColumModel(List<String> categorias, String name, List<Integer> data) {
		super();
		this.categorias = categorias;
		this.name = name;
		this.data = data;
	}


	public List<String> getCategorias() {
		return categorias;
	}


	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Integer> getData() {
		return data;
	}


	public void setData(List<Integer> data) {
		this.data = data;
	}
	
	
	
}
