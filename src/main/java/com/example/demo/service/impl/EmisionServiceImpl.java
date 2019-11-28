package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.EmisionService;

@Service("emisionServiceImpl")
public class EmisionServiceImpl implements EmisionService {

	private static final Logger logger = LogManager.getLogger(EmisionServiceImpl.class);

	@Autowired
	private XxmpfBpmIndEmisionRepository emisionRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<String> findDistincSectorByFechaInicio(String dateStart, String dateFinish) { // TODO Auto-generated
																								// method stub
		return emisionRepository.distinctSectorByFechaInicio(dateStart, dateFinish);
	}

	@Override
	public List<XxmpfBpmIndEmision> findAllEmisionByFechaInicio(String dateStart, String dateFinish) {
		logger.info("Method findAllEmisionByFechaInicio param [ " + dateStart + " - " + dateFinish);
		return emisionRepository.findAllEmisionByFechaInicio(dateStart, dateFinish);
	}

	@Override
	public List<String> findDistincSectorByFechaFin(String dateStart, String dateFinish) {
		// TODO Auto-generated method stub
		return emisionRepository.distinctSectorByFechaFin(dateStart, dateFinish);
	}

	@Override
	public List<XxmpfBpmIndEmision> findAllEmisionByFechaFin(String dateStart, String dateFinish) {
		// TODO Auto-generated method stub
		return emisionRepository.findAllEmisionByFechaFin(dateStart, dateFinish);
	}

	@Override
	public Integer countBySector(List<XxmpfBpmIndEmision> listEmision, String sector) {
		logger.info(
				"Method countBySector param[ listEmision.size = " + listEmision.size() + " Sector = " + sector + " ]");
		Long count = 0L;
		if (sector == null) {
			count = listEmision.stream().filter(emision -> emision.getSector() == null).count();
		} else {
			count = listEmision.stream().filter(emision -> emision.getSector().equals(sector)).count();
		}
		return count.intValue();
	}

	@Override
	public List<String> distinctStatusByFechfinAndaSector(String dateStart, String dateFinish, String sector) {
		return emisionRepository.distinctStatusByFechaFinAndSector(dateStart, dateFinish, sector);
	}

	@Override
	public Integer countBySectorAndStatus(List<XxmpfBpmIndEmision> listEmision, String sector, String status) {
		Long count = listEmision.stream()
				.filter(emision -> emision.getSector().equals(sector) && emision.getEstatus().equals(status)).count();
		return count.intValue();
	}
	
	@Override
	public List<Object []> allByEstatus(String status){
		List<Object[]> results = entityManager.
				createQuery("SELECT emision.folio, emision.sector, emision.tipoSolicitud, emision.divisional, emision.regional, "
						          + "emision.oficinaComercial, emision.agente, emision.estatus, emision.motivo, "
						          + "detalle.usuarioAnalista ,detalle.usuarioEmisor, detalle.usuarioSuscriptor, emision.fechaFin"
						+ " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND"
						      + " detalle.area = 'Emisión' AND "
						      + " detalle.estatus = '"+status+"'"
						     ).getResultList();
		return results;
	}
	
	@Override
	public Integer countSector( List<Object[]> listEmision, String sector, int indexSector) {
		//int count = 0;
		
		Long count = listEmision.stream().filter(emision-> emision[indexSector].toString().equals(sector)).count();
		return count.intValue();
		/*for(Object[] emsion :listEmision ) {
			if((emsion[indexSector].toString()).equals(sector)) {
				count++;
			}
		}
		logger.info("Method countSector count = "+count+" sector = "+sector);
		return count*/
	}
}
