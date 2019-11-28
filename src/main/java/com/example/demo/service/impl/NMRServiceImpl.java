package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.model.DateModel;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.EmisionService;
import com.example.demo.service.NMRService;

@Service
public class NMRServiceImpl implements NMRService{
	
	@PersistenceContext
    private EntityManager entityManager;

	@Autowired
	private XxmpfBpmIndEmisionRepository repository;
	
	@Autowired
	private EmisionService emisionService;
	
	@Override
	public List<String> sectorAll() {
		// TODO Auto-generated method stub
		return repository.distinctSector();
	}

	@Override
	public List<XxmpfBpmIndEmision> allByFechaFin(DateModel dates){
		return repository.findAllByAreaAndEstatusAndFechaFin(dates.getDateStart(), dates.getDateFinish());
	}
	 
	@Override
	public Integer countBySector(List<XxmpfBpmIndEmision> listEmision, String sector) {
		// TODO Auto-generated method stub
		return emisionService.countBySector(listEmision, sector);
	}
	
	@Override
	public List<Object []> allNumMovimientosRealizados(DateModel date){
		List<Object[]> results = entityManager.
				createQuery("SELECT emision.folio, emision.sector, emision.tipoSolicitud, emision.divisional, emision.regional, "
						          + "emision.oficinaComercial, emision.agente, emision.estatus, emision.motivo, "
						          + "detalle.usuarioAnalista ,detalle.usuarioEmisor, detalle.usuarioSuscriptor, emision.fechaFin"
						+ " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND"
						      + " detalle.area = 'Emisión' AND "
						      + " detalle.estatus = 'Emitido' AND"
						      + " emision.estatus = 'Emitido' AND"
						      + " emision.fechaFin  > TO_DATE('"+date.getDateStart()+"', 'DD/MM/YY') AND"
						      + " emision.fechaFin  < TO_DATE('"+date.getDateFinish()+"', 'DD/MM/YY') "
						     ).getResultList();
		return results;
	}

}
