package com.example.demo.model;

public class FPRModel {

	private String sector;
	private String rangoHorario;
	private int folioRecibido;
	private int folioAtendido;
	
	public FPRModel() {}
	
	public FPRModel(String sector, String rangoHorario, int folioRecibido, int folioAtendido) {
		this.sector = sector;
		this.rangoHorario = rangoHorario;
		this.folioRecibido = folioRecibido;
		this.folioAtendido = folioAtendido;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getRangoHorario() {
		return rangoHorario;
	}

	public void setRangoHorario(String rangoHorario) {
		this.rangoHorario = rangoHorario;
	}

	public int getFolioRecibido() {
		return folioRecibido;
	}

	public void setFolioRecibido(int folioRecibido) {
		this.folioRecibido = folioRecibido;
	}

	public int getFolioAtendido() {
		return folioAtendido;
	}

	public void setFolioAtendido(int folioAtendido) {
		this.folioAtendido = folioAtendido;
	}
	
	
}
