package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class ChartBarraModel {

	private String name;
	private Integer[] data;
	
	public ChartBarraModel() {
		super();
	}

	public ChartBarraModel(String name, Integer[] data) {
		super();
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getData() {
		return data;
	}

	public void setData(Integer[] data) {
		this.data = data;
	}

	
	
	
	
}
