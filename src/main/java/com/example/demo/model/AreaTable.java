package com.example.demo.model;

public class AreaTable {

	private Integer id;
	
	private String area;
	
	private Integer dias;

	public AreaTable() {
		super();
	}

	public AreaTable(Integer id, String area, Integer dias) {
		super();
		this.id = id;
		this.area = area;
		this.dias = dias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}
	
	
}
