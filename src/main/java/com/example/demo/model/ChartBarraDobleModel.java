package com.example.demo.model;

import java.util.List;

public class ChartBarraDobleModel {

	private String name;
	private List<Integer> data;
	
	public ChartBarraDobleModel( List<Integer> data,String name) {
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

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}
	
	
}
