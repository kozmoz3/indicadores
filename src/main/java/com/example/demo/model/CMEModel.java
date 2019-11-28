package com.example.demo.model;

public class CMEModel {

	private String sector;
	private String periodo;
	
	public CMEModel() {}
	
	public CMEModel(String sector, String periodo) {
		super();
		this.sector = sector;
		this.periodo = periodo;
	}


	public String getSector() {
		return sector;
	}


	public void setSector(String sector) {
		this.sector = sector;
	}


	public String getPeriodo() {
		return periodo;
	}


	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
}
