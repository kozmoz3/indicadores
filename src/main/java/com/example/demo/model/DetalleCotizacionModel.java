package com.example.demo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DetalleCotizacionModel  {

	@NotNull
    @Size(min=8, max=8)
	@Pattern(regexp ="^([0-2][0-9]|3[0-1])(/)(0[1-9]|1[0-2])(/)(\\d{2})")
	private String dateStart;
	
	@NotNull
    @Size(min=8, max=8)
	@Pattern(regexp ="^([0-2][0-9]|3[0-1])(/)(0[1-9]|1[0-2])(/)(\\d{2})")
	private String dateFinish;
	
	private String detalle;
	
	private Integer total;
	
	public  DetalleCotizacionModel() {}

	
	public DetalleCotizacionModel(String dateStart, String dateFinish) {
		super();
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
	}


	public DetalleCotizacionModel(String dateStart, String dateFinish, String detalle) {
		super();
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.detalle = detalle;
	}

	
	public DetalleCotizacionModel(String dateStart, String dateFinish, String detalle, Integer total) {
		super();
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
		this.detalle = detalle;
		this.total = total;
	}


	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
}
