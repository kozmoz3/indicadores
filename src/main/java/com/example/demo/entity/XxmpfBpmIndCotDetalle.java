package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "xxmpf_Bpm_Ind_Cot_Detalle")
public class XxmpfBpmIndCotDetalle {

	@Id
    @Column(name="ID_COTIZACION_DETALLE")
	private Integer idCotizacion;
	
	@ManyToOne
	@MapsId("ID_COTIZACION")
	private XxmpfBpmIndCotizacion idCotizacionFK;
	

	@Column(name="AREA")
	private String area;
	
	@Column(name="ACTIVIDAD")
	private String actividad;
	
	@Column(name="ESTATUS")
	private String estatus;
	
	@Column(name="MOTIVO")
	private String motivo;
	
	@Column(name="USUARIO_ANALISTA")
	private String usuarioAnalista;
	
	@Column(name="NOMBRE_ANALISTA")
	private String nombreanalista;
	
	@Column(name="USUARIO_SUSCRIPTOR")
	private String usuarioSuscriptor;
	
	@Column(name="NOMBRE_SUSCRIPTOR")
	private String nombreSuscriptor;
	
	@Column(name="USUARIO_EMISOR")
	private String usuarioEmisor;
	
	@Column(name="NOMBRE_EMISOR")
	private String nombreEmisor;
	
	@Column(name="FECHA_INICIO")
	private String fechaInicio;
	
	@Column(name="FECHA_FIN")
	private String fechaFin;
	
	@Column(name="TIEMPO_ACTIVIDAD")
	private Integer tiempoActividad;
	
	@Column(name="TIEMPO_DIAS")
	private Integer tiempoDias;

	public XxmpfBpmIndCotDetalle() {
		super();
	}

	public XxmpfBpmIndCotDetalle(Integer idCotizacion, XxmpfBpmIndCotizacion idCotizacionFK, String area,
			String actividad, String estatus, String motivo, String usuarioAnalista, String nombreanalista,
			String usuarioSuscriptor, String nombreSuscriptor, String usuarioEmisor, String nombreEmisor,
			String fechaInicio, String fechaFin, Integer tiempoActividad) {
		super();
		this.idCotizacion = idCotizacion;
		this.idCotizacionFK = idCotizacionFK;
		this.area = area;
		this.actividad = actividad;
		this.estatus = estatus;
		this.motivo = motivo;
		this.usuarioAnalista = usuarioAnalista;
		this.nombreanalista = nombreanalista;
		this.usuarioSuscriptor = usuarioSuscriptor;
		this.nombreSuscriptor = nombreSuscriptor;
		this.usuarioEmisor = usuarioEmisor;
		this.nombreEmisor = nombreEmisor;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tiempoActividad = tiempoActividad;
	}

	public Integer getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Integer idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public XxmpfBpmIndCotizacion getIdCotizacionFK() {
		return idCotizacionFK;
	}

	public void setIdCotizacionFK(XxmpfBpmIndCotizacion idCotizacionFK) {
		this.idCotizacionFK = idCotizacionFK;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getUsuarioAnalista() {
		return usuarioAnalista;
	}

	public void setUsuarioAnalista(String usuarioAnalista) {
		this.usuarioAnalista = usuarioAnalista;
	}

	public String getNombreanalista() {
		return nombreanalista;
	}

	public void setNombreanalista(String nombreanalista) {
		this.nombreanalista = nombreanalista;
	}

	public String getUsuarioSuscriptor() {
		return usuarioSuscriptor;
	}

	public void setUsuarioSuscriptor(String usuarioSuscriptor) {
		this.usuarioSuscriptor = usuarioSuscriptor;
	}

	public String getNombreSuscriptor() {
		return nombreSuscriptor;
	}

	public void setNombreSuscriptor(String nombreSuscriptor) {
		this.nombreSuscriptor = nombreSuscriptor;
	}

	public String getUsuarioEmisor() {
		return usuarioEmisor;
	}

	public void setUsuarioEmisor(String usuarioEmisor) {
		this.usuarioEmisor = usuarioEmisor;
	}

	public String getNombreEmisor() {
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getTiempoActividad() {
		return tiempoActividad;
	}

	public void setTiempoActividad(Integer tiempoActividad) {
		this.tiempoActividad = tiempoActividad;
	}

	public Integer getTiempoDias() {
		return tiempoDias;
	}

	public void setTiempoDias(Integer tiempoDias) {
		this.tiempoDias = tiempoDias;
	}
	
	
	
}
