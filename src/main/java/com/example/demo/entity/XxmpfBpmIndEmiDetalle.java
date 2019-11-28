package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "xxmpf_Bpm_Ind_Emi_Detalle")
public class XxmpfBpmIndEmiDetalle {

	@Id
    @Column(name="ID_EMISION_DETALLE")
	private Integer idEmiDetalle;
	
	//@Column(name="ID_EMISION")
	@ManyToOne
	@MapsId("ID_EMISION")
	@JoinColumn(name = "ID_EMISION", insertable = false, updatable = false)
	private XxmpfBpmIndEmision idEmisionFK;
	
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
	
	@Column(name="ACTIVIDAD_ATIEMPO")
	private Integer actividadATiempo;
	
	@Column(name="TIEMPO_ATENCION")
	private Integer tiempoAtencion; 
	
	
	
	public Integer getTiempoAtencion() {
		return tiempoAtencion;
	}

	public void setTiempoAtencion(Integer tiempoAtencion) {
		this.tiempoAtencion = tiempoAtencion;
	}

	public XxmpfBpmIndEmiDetalle() {
		
	}

	public XxmpfBpmIndEmiDetalle(Integer idEmiDetalle, XxmpfBpmIndEmision idEmisionFK, String area, String actividad,
			String estatus, String motivo, String usuarioAnalista, String nombreanalista, String usuarioSuscriptor,
			String nombreSuscriptor, String usuarioEmisor, String nombreEmisor, String fechaInicio, String fechaFin,
			Integer tiempoActividad) {
		super();
		this.idEmiDetalle = idEmiDetalle;
		this.idEmisionFK = idEmisionFK;
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

	public Integer getIdEmiDetalle() {
		if(idEmiDetalle == null)
			return 0;
		else
		return idEmiDetalle;
	}

	public void setIdEmiDetalle(Integer idEmiDetalle) {
		this.idEmiDetalle = idEmiDetalle;
	}

	public XxmpfBpmIndEmision getIdEmisionFK() {
		
		return idEmisionFK;
	}

	public void setIdEmisionFK(XxmpfBpmIndEmision idEmisionFK) {
		this.idEmisionFK = idEmisionFK;
	}

	public String getArea() {
		if(area == null)
			return "";
		else
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getActividad() {
		if(actividad == null)
			return "";
		else
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public String getEstatus() {
		if(estatus == null)
			return "";
		else
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getMotivo() {
		if(motivo == null)
			return "";
		else
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getUsuarioAnalista() {
		if(usuarioAnalista == null)
			return "";
		else
		return usuarioAnalista;
	}

	public void setUsuarioAnalista(String usuarioAnalista) {
		this.usuarioAnalista = usuarioAnalista;
	}

	public String getNombreanalista() {
		if(nombreanalista == null)
			return "";
		else
		return nombreanalista;
	}

	public void setNombreanalista(String nombreanalista) {
		this.nombreanalista = nombreanalista;
	}

	public String getUsuarioSuscriptor() {
		if(usuarioSuscriptor == null)
			return "";
		else
		return usuarioSuscriptor;
	}

	public void setUsuarioSuscriptor(String usuarioSuscriptor) {
		this.usuarioSuscriptor = usuarioSuscriptor;
	}

	public String getNombreSuscriptor() {
		if(nombreSuscriptor == null)
			return "";
		else
		return nombreSuscriptor;
	}

	public void setNombreSuscriptor(String nombreSuscriptor) {
		this.nombreSuscriptor = nombreSuscriptor;
	}

	public String getUsuarioEmisor() {
		if(usuarioEmisor == null)
			return "";
		else
		return usuarioEmisor;
	}

	public void setUsuarioEmisor(String usuarioEmisor) {
		this.usuarioEmisor = usuarioEmisor;
	}

	public String getNombreEmisor() {
		if(nombreEmisor == null)
			return "";
		else
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) {
		this.nombreEmisor = nombreEmisor;
	}

	public String getFechaInicio() {
		if(fechaInicio == null)
			return "";
		else
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		if(fechaFin == null)
			return "";
		else
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getTiempoActividad() {
		if(tiempoActividad == null)
			return 0;
		else
		return tiempoActividad;
	}

	public void setTiempoActividad(Integer tiempoActividad) {
		this.tiempoActividad = tiempoActividad;
	}

	public Integer getActividadATiempo() {
		return actividadATiempo;
	}

	public void setActividadATiempo(Integer actividadATiempo) {
		this.actividadATiempo = actividadATiempo;
	}
	
	
	
}
