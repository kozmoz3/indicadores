package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class CotizacionModel {

	private String estatus;
	private int numFolios;
	private String porcentaje;
	
	public CotizacionModel() {}
	
	public CotizacionModel(String estatus, int numFolios, String porcentaje) {
		super();
		this.estatus = estatus;
		this.numFolios = numFolios;
		this.porcentaje = porcentaje;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getNumFolios() {
		return numFolios;
	}

	public void setNumFolios(int numFolios) {
		this.numFolios = numFolios;
	}

	public String getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(String porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	
}
