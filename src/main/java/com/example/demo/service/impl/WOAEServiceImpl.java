package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.service.WOAEService;

@Service
public class WOAEServiceImpl implements WOAEService{
	
	private static final Logger logger = LogManager.getLogger(WOAEServiceImpl.class);

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Object[]>getFoliosMesActual(String paramSelect,String estatus){
		List<Object[]> results = entityManager.
				createQuery(" SELECT COUNT(emision.folio), emision."+paramSelect
						+ " FROM  XxmpfBpmIndEmiDetalle detalle,"
						+ " XxmpfBpmIndEmision emision"
						+ " WHERE  emision.idEmision = detalle.idEmisionFK AND"
						      + " detalle.area = 'Emisión' AND"
						      + " detalle.estatus = '"+estatus+"' AND"
						      + " detalle.fechaFin BETWEEN TRUNC(SYSDATE, 'mm') AND"
						      + " SYSDATE "
						      + " GROUP BY emision."+paramSelect
						      + " ORDER BY 1 DESC"
						     ).getResultList();
		return results;
	}
	
	@Override
	public List<Object[]>getFoliosMesActualAnalista(String paramSelect,String estatus){
		List<Object[]> results = entityManager.
				createQuery(" SELECT COUNT(emision.folio), detalle."+paramSelect
						+ " FROM  XxmpfBpmIndEmiDetalle detalle,"
						+ " XxmpfBpmIndEmision emision"
						+ " WHERE  emision.idEmision = detalle.idEmisionFK AND"
						      + " detalle.area = 'Emisión' AND"
						      + " detalle.estatus = '"+estatus+"' AND"
						      + " detalle.fechaFin BETWEEN TRUNC(SYSDATE, 'mm') AND"
						      + " SYSDATE "
						      + " GROUP BY detalle."+paramSelect
						      + " ORDER BY 1 DESC"
						     ).getResultList();
		return results;
	}
	
	@Override
	public List<Object[]>getFoliosMesPasado(String paramSelect,String estatus){
		List<Object[]> results = entityManager.
				createQuery("SELECT Count(emision.folio), emision."+paramSelect
						+ " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND"
						      + " detalle.area = 'Emisión' AND "
						      + " detalle.estatus = '"+estatus+"' AND"
						      + " detalle.fechaFin BETWEEN ADD_MONTHS(TRUNC(SYSDATE,'mm'),-1) AND"
						      + " TRUNC(SYSDATE, 'mm') "
						      + " GROUP BY emision."+paramSelect
						      + " ORDER BY 1 DESC"
						     // + " WHERE ROWNUM <=5;"
						     ).getResultList();
		return results;
	}
	
	
	@Override
	public List<Object[]>getFoliosMesAnteriorAnalista(String paramSelect,String estatus){
		
		List<Object[]> results = entityManager.
				createQuery("SELECT Count(emision.folio), detalle."+paramSelect
						+ " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND"
						      + " detalle.area = 'Emisión' AND "
						      + " detalle.estatus = '"+estatus+"' AND"
						      + " detalle.fechaFin BETWEEN ADD_MONTHS(TRUNC(SYSDATE,'mm'),-1) AND"
						      + " TRUNC(SYSDATE, 'mm') "
						      + " GROUP BY detalle."+paramSelect
						      + " ORDER BY 1 DESC"
						     // + " WHERE ROWNUM <=5;"
						     ).getResultList();
		return results;
	}
}
