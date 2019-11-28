package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class ChartModel {

	private int id;
	private String name;
	private double y;
	
	public  ChartModel() {
	}
	
	
	
	public ChartModel(int id, String name, double y) {
		super();
		this.id = id;
		this.name = name;
		this.y = y;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	
}
