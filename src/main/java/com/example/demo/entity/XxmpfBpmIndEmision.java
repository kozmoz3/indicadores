package com.example.demo.entity;

import java.util.List;
import java.util.Optional;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "xxmpf_Bpm_Ind_Emision")
public class XxmpfBpmIndEmision {

	@Id
    @Column(name="ID_EMISION")
	private Integer idEmision;
	
	@OneToMany(mappedBy = "idEmisionFK")
	@Cascade(CascadeType.ALL)
	private List<XxmpfBpmIndEmiDetalle>listDetalle;
		

	@Column(name="FOLIO")
	private String folio;
	
	@Column(name="ESTATUS")
	private String estatus;
	
	@Column(name="MOTIVO")
	private String motivo;
	
	@Column(name="TIPO_SOLICITUD")
	private String tipoSolicitud;
	
	@Column(name="TIPO_POLIZA")
	private String tipoPoliza;
	
	@Column(name="NUM_RIESGOS")
	private Integer numRiesgos;
	
	@Column(name="COD_SECTOR")
	private Integer codSector;
	
	@Column(name="SECTOR")
	private String sector;
		
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
	
	@Column(name="DEVOLUCIONES_SUS_AC")
	private Integer devolucionesSusAc;
	
	@Column(name="DEVOLUCIONES_EM_AC")
	private Integer  devolucionesEmAc;
	
	@Column(name="DEVOLUCIONES_EM_SUS")
	private Integer devolucionesEmSus;
	
	@Column(name="CANAL_ENTRADA")
	private String canalEntrada;
	
	@Column(name="FOLIO_COT")
	private String folioCot;
	
	@Column(name="FECHA_INICIO")
	private String fechaInicio;
	
	@Column(name="FECHA_ULT_MOD")
	private String fechaUltMod;
	
	
	
	@Column(name="FECHA_FIN")
	private String fechaFin;
	
	@Column(name="TIEMPO_T_FOLIO")
	private Integer tiempoTFolio;
	
	@Column(name="TIEMPO_DIAS")
	private Integer tiempoDias;
	
	@Column(name="ERROR_CAPTURA")
	private Integer errorCaptura;
	
	
	
	@Column(name="TIPO_ERROR_CAPTURA")
	private String tipoErrorCaptura;
	
	
	public XxmpfBpmIndEmision() {
		
	}
	
	

	public Integer getIdEmision() {
         if(idEmision == null)
        	 return 0;
         else
		return idEmision;
	}

	public void setIdEmision(Integer idEmision) {
		this.idEmision = idEmision;
	}

	public String getFolio() {
		if(folio == null)
       	 return "";
        else
        	return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
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

	public String getTipoSolicitud() {
		if(tipoSolicitud == null)
	       	 return "";
	        else
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public String getTipoPoliza() {
		if(tipoPoliza == null)
	       	 return "";
	        else
		return tipoPoliza;
	}

	public void setTipoPoliza(String tipoPoliza) {
		this.tipoPoliza = tipoPoliza;
	}

	public Integer getNumRiesgos() {
		if(numRiesgos == null)
	       	 return 0;
	        else
		return numRiesgos;
	}

	public void setNumRiesgos(Integer numRiesgos) {
		this.numRiesgos = numRiesgos;
	}

	public Integer getCodSector() {
		if(codSector == null)
	       	 return 0;
	        else
		return codSector;
	}

	public void setCodSector(Integer codSector) {
		this.codSector = codSector;
	}

	public Integer getCveDivisional() {
		if(cveDivisional == null)
	       	 return 0;
	        else
		return cveDivisional;
	}

	public void setCveDivisional(Integer cveDivisional) {
		this.cveDivisional = cveDivisional;
	}

	public String getDivisional() {
		if(divisional == null)
	       	 return "";
	        else
		return divisional;
	}

	public void setDivisional(String divisional) {
		this.divisional = divisional;
	}

	public Integer getCveRegional() {
		if(cveRegional == null)
	       	 return 0;
	        else
		return cveRegional;
	}

	public void setCveRegional(Integer cveRegional) {
		this.cveRegional = cveRegional;
	}

	public String getRegional() {
		if(regional == null)
	       	 return "";
	        else
		return regional;
	}

	public void setRegional(String regional) {
		this.regional = regional;
	}

	public Integer getCveOficinaComercial() {
		if(cveOficinaComercial == null)
	       	 return 0;
	        else
		return cveOficinaComercial;
	}

	public void setCveOficinaRegional(Integer cveOficinaComercial) {
		this.cveOficinaComercial = cveOficinaComercial;
	}

	public String getOficinaComercial() {
		if(oficinaComercial == null)
	       	 return "";
	        else
		return oficinaComercial;
	}

	public void setOficinaComercial(String oficinaComercial) {
		this.oficinaComercial = oficinaComercial;
	}

	public Integer getCveAgente() {
		if(cveAgente == null)
	       	 return 0;
	        else
		return cveAgente;
	}

	public void setCveAgente(Integer cveAgente) {
		this.cveAgente = cveAgente;
	}

	public String getAgente() {
		if(agente == null)
	       	 return "";
	        else
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public String getRamo() {
		if(ramo == null)
	       	 return "";
	        else
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getUso() {
		if(uso == null)
	       	 return "";
	        else
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}

	public Integer getDevolucionesSusAc() {
		if(devolucionesSusAc == null)
	       	 return 0;
	        else
		return devolucionesSusAc;
	}

	public void setDevolucionesSusAc(Integer devolucionesSusAc) {
		this.devolucionesSusAc = devolucionesSusAc;
	}

	public Integer getDevolucionesEmAc() {
		if(devolucionesEmAc == null)
	       	 return 0;
	        else
		return devolucionesEmAc;
	}

	public void setDevolucionesEmAc(Integer devolucionesEmAc) {
		this.devolucionesEmAc = devolucionesEmAc;
	}

	public Integer getDevolucionesEmSus() {
		if(devolucionesEmSus == null)
	       	 return 0;
	        else
		return devolucionesEmSus;
	}

	public void setDevolucionesEmSus(Integer devolucionesEmSus) {
		this.devolucionesEmSus = devolucionesEmSus;
	}

	public String getCanalEntrada() {
		if(canalEntrada == null)
	       	 return "";
	        else
		return canalEntrada;
	}

	public void setCanalEntrada(String canalEntrada) {
		this.canalEntrada = canalEntrada;
	}

	public String getFolioCot() {
		if(folioCot == null)
	       	 return "";
	        else
		return folioCot;
	}

	public void setFolioCot(String folioCot) {
		this.folioCot = folioCot;
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

	public String getFechaUltMod() {
		if(fechaUltMod == null)
	       	 return "";
	        else
		return fechaUltMod;
	}

	public void setFechaUltMod(String fechaUltMod) {
		this.fechaUltMod = fechaUltMod;
	}

	

	public String getFechaFin() {
		if(fechaFin == null)
	       	 return "";
	        else
		return fechaFin.substring(0, 20);
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getTiempoTFolio() {
		if(tiempoTFolio == null)
	       	 return 0;
	        else
		return tiempoTFolio;
	}

	public void setTiempoTFolio(Integer tiempoTFolio) {
		this.tiempoTFolio = tiempoTFolio;
	}

	

	

	public Integer getErrorCaptura() {
		return errorCaptura;
	}



	public void setErrorCaptura(Integer errorCaptura) {
		this.errorCaptura = errorCaptura;
	}



	public String getTipoErrorCaptura() {
		return tipoErrorCaptura;
	}



	public void setTipoErrorCaptura(String tipoErrorCaptura) {
		this.tipoErrorCaptura = tipoErrorCaptura;
	}



	public Integer getTiempoDias() {
		if(tiempoDias == null)
	       	 return 0;
	        else
		return tiempoDias;
	}

	public void setTiempoDias(Integer tiempoDias) {
		this.tiempoDias = tiempoDias;
	}

	public String getSector() {
		if(sector == null)
	       	 return "";
	        else
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
	
	public List<XxmpfBpmIndEmiDetalle> getListDetalle() {
		return listDetalle;
	}



	public void setListDetalle(List<XxmpfBpmIndEmiDetalle> listDetalle) {
		this.listDetalle = listDetalle;
	}



	public void setCveOficinaComercial(Integer cveOficinaComercial) {
		this.cveOficinaComercial = cveOficinaComercial;
	}
	
}
