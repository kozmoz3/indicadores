package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.impl.ChartPFPImpl;
import com.example.demo.service.PFPService;

@Service
public class PFPServiceImpl implements PFPService{
	
	private static final Logger logger = LogManager.getLogger(PFPServiceImpl.class);
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public List<Object []> allFoliosPendiente(){
		List<Object[]> results = entityManager.
				createQuery("SELECT emision.folio, emision.sector, emision.tipoSolicitud, emision.divisional, emision.regional, "
						          + "emision.oficinaComercial, emision.agente, emision.estatus, emision.motivo, "
						          + "detalle.usuarioAnalista ,detalle.usuarioEmisor, detalle.usuarioSuscriptor, emision.fechaFin"
						+ " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND"
						      + " detalle.area = 'Emisión' AND "
						      + " detalle.estatus = 'Pendiente'"
						     ).getResultList();
		return results;
	}
	
	@Override
	public List<String> distinctSector(){
		List<String> resultList= entityManager.
			      createQuery("SELECT DISTINCT(emision.sector)"
			      	    	+ "  FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle "
			      	    	+ "  WHERE emision.idEmision = detalle.idEmisionFK  AND "
			      	          	+ "  detalle.area = 'Emisión' AND "
			      	          + " detalle.estatus = 'Pendiente'").getResultList();		    
			  return resultList;
	}
	
	@Override
	public Integer countSector( List<Object[]> listEmision, String sector) {
		int count = 0;
		for(Object[] emsion :listEmision ) {
			if((emsion[1].toString()).equals(sector)) {
				count++;
			}
		}
		logger.info("Method countSector count = "+count+" sector = "+sector);
		return count;
	}
	
}
