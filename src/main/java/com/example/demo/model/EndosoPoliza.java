package com.example.demo.model;

public class EndosoPoliza {

	private int count;
	
	private String solicitud;
	
	public EndosoPoliza() {}

	public EndosoPoliza(int count, String solicitud) {
		super();
		this.count = count;
		this.solicitud = solicitud;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}
	
	
}
