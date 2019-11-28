package com.example.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Retrabajo {
 
	private String recotizacion;
	private int folios;
	
	public Retrabajo() {}

	public Retrabajo(String recotizacion, int folios) {
		super();
		this.recotizacion = recotizacion;
		this.folios = folios;
	}

	public String getRecotizacion() {
		return recotizacion;
	}

	public void setRecotizacion(String recotizacion) {
		this.recotizacion = recotizacion;
	}

	public int getFolios() {
		return folios;
	}

	public void setFolios(int folios) {
		this.folios = folios;
	}
	
	
}
