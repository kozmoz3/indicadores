package com.example.demo.model;

import java.util.List;

public class ChartCombinationModel {

	private String name;
	
	private String type;
	
	private String color;
	
	private List<Integer> data;
	
	private List<String> categories;
	
	

	public ChartCombinationModel(String name, String type, String color, List<Integer> data, List<String> categories) {
		super();
		this.name = name;
		this.type = type;
		this.color = color;
		this.data = data;
		this.categories = categories;
	}

	public ChartCombinationModel(String name, String type, String color, List<Integer> data) {
		super();
		this.name = name;
		this.type = type;
		this.color = color;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	
	
	
}
