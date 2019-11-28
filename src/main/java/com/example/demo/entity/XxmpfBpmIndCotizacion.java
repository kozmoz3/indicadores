package com.example.demo.entity;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "xxmpf_Bpm_Ind_Cotizacion")
public class XxmpfBpmIndCotizacion {
	
    @Id
    @Column(name="ID_COTIZACION")
	private Integer idCotizacion;
    
    @OneToMany(mappedBy = "idCotizacionFK")
    List<XxmpfBpmIndCotDetalle> xxmpfBpmIndCotDetalle;
    
    @Column(name="FOLIO")
	private String folio;
    
    @Column(name="ESTATUS")
	private String status;
    
    @Column(name="MOTIVO")
	private String motivo;
    
    @Column(name="TIPO_SOLICITUD")
	private String tipoSolicitud;
    
    @Column(name="TIPO_POLIZA")
	private String tipoPoliza;
    
    @Column(name="COD_SECTOR")
	private Integer codSector;
    
    @Column(name="CVE_DIVISIONAL")
	private Integer cveDivisional;
    
    @Column(name="DIVISIONAL")
	private String divisional;
    
    @Column(name="CVE_REGIONAL")
	private Integer cveRegional;
    
    @Column(name="REGIONAL")
	private String regional;
    
    @Column(name="CVE_OFICINA_COMERCIAL")
	private Integer cveOficinaComercial;
    
    @Column(name="OFICINA_COMERCIAL")
	private String oficinaComercial;
    
    @Column(name="CVE_AGENTE")
	private Integer cveAgente;
    
    @Column(name="AGENTE")
	private String agente;
    
    @Column(name="RAMO")
	private String ramo;
    
    @Column(name="USO")
	private String uso;
    
    @Column(name="BANDERA_RECOT")
	private Integer banderaRecot;
    
    @Column(name="RECOTIZACIONES")
	private Integer recotizaciones;
    
    @Column(name="BANDERA_DEV")
	private Integer banderaDev;
    
    @Column(name="DEVOLUCIONES")
	private Integer devoluciones;
    
    @Column(name="CANAL_ENTRADA")
	private String canalEntrada;
    
    @Column(name="TIEMPO_FOLIO")
	private Integer tiempoFolio;
    
    @Column(name="FECHA_INICIO")
    private String fechaInicio;
    
    @Column(name="FECHA_FIN")
    private String fechaFin;
    
    @Column(name="TIEMPO_DIAS")
   	private Integer tiempoDias;
    
    @Column(name="SECTOR")
   	private String sector;
	
	public void XmpfBpmIndCotizacion() {}

	public Integer getIdCotizacion() {
		return idCotizacion;
	}

	public void setIdCotizacion(Integer idCotizacion) {
		this.idCotizacion = idCotizacion;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public String getTipoPoliza() {
		return tipoPoliza;
	}

	public void setTipoPoliza(String tipoPoliza) {
		this.tipoPoliza = tipoPoliza;
	}

	public Integer getCodSector() {
		return codSector;
	}

	public void setCodSector(Integer codSector) {
		this.codSector = codSector;
	}

	public Integer getCveDivisional() {
		return cveDivisional;
	}

	public void setCveDivisional(Integer cveDivisional) {
		this.cveDivisional = cveDivisional;
	}

	public String getDivisional() {
		return divisional;
	}

	public void setDivisional(String divisional) {
		this.divisional = divisional;
	}

	public Integer getCveRegional() {
		return cveRegional;
	}

	public void setCveRegional(Integer cveRegional) {
		this.cveRegional = cveRegional;
	}

	public String getRegional() {
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public Integer getCveOficinaComercial() {
		return cveOficinaComercial;
	}

	public void setCveOficinaComercial(Integer cveOficinaComercial) {
		this.cveOficinaComercial = cveOficinaComercial;
	}

	public String getOficinaComercial() {
		return oficinaComercial;
	}

	public void setOficinaComercial(String oficinaComercial) {
		this.oficinaComercial = oficinaComercial;
	}

	public Integer getCveAgente() {
		return cveAgente;
	}

	public void setCveAgente(Integer cveAgente) {
		this.cveAgente = cveAgente;
	}

	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}

	public Integer getBanderaRecot() {
		return banderaRecot;
	}

	public void setBanderaRecot(Integer banderaRecot) {
		this.banderaRecot = banderaRecot;
	}

	public Integer getRecotizaciones() {
		return recotizaciones;
	}

	public void setRecotizaciones(Integer recotizaciones) {
		this.recotizaciones = recotizaciones;
	}

	public Integer getBanderaDev() {
		return banderaDev;
	}

	public void setBanderaDev(Integer banderaDev) {
		this.banderaDev = banderaDev;
	}

	public Integer getDevoluciones() {
		return devoluciones;
	}

	public void setDevoluciones(Integer devoluciones) {
		this.devoluciones = devoluciones;
	}

	public String getCanalEntrada() {
		return canalEntrada;
	}

	public void setCanalEntrada(String canalEntrada) {
		this.canalEntrada = canalEntrada;
	}

	public Integer getTiempoFolio() {
		return tiempoFolio;
	}

	public void setTiempoFolio(Integer tiempoFolio) {
		this.tiempoFolio = tiempoFolio;
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

	public Integer getTiempoDias() {
		return tiempoDias;
	}

	public void setTiempoDias(Integer tiempoDias) {
		this.tiempoDias = tiempoDias;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public List<XxmpfBpmIndCotDetalle> getXxmpfBpmIndCotDetalle() {
		return xxmpfBpmIndCotDetalle;
	}

	public void setXxmpfBpmIndCotDetalle(List<XxmpfBpmIndCotDetalle> xxmpfBpmIndCotDetalle) {
		this.xxmpfBpmIndCotDetalle = xxmpfBpmIndCotDetalle;
	}
	
	
	
}
