package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.demo.service.FTEService;

@Service
public class FTEServiceImpl implements FTEService{

	private static final Logger logger = LogManager.getLogger(FTEServiceImpl.class);

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Object[]>getFoliosMesActual(String sector){
		List<Object[]> results = entityManager.
				createQuery(" SELECT COUNT(detalle.idEmiDetalle), SUM(emision.numRiesgos),detalle.usuarioEmisor"
						+ " FROM  XxmpfBpmIndEmiDetalle detalle,"
						+ " XxmpfBpmIndEmision emision"
						+ " WHERE  emision.idEmision = detalle.idEmisionFK AND"
						      + " detalle.area = 'Emisión' AND"
						      + " detalle.estatus != 'Pendiente' AND"
						      + " detalle.fechaFin BETWEEN TRUNC(SYSDATE, 'mm') AND"
						      + " SYSDATE AND "
						      + " emision.sector = '"+sector+"'"
						      + " GROUP BY detalle.usuarioEmisor"
						      + " ORDER BY 3"
						     ).getResultList();
		return results;
	}
	
	@Override
	public List<Object[]>getFoliosMesPasado(String sector){
		List<Object[]> results = entityManager.
				createQuery("SELECT COUNT(detalle.idEmiDetalle), SUM(emision.numRiesgos),detalle.usuarioEmisor"
						+ " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND"
						      + " detalle.area = 'Emisión' AND "
						      + " detalle.estatus != 'Pendiente' AND"
						      + " detalle.fechaFin BETWEEN ADD_MONTHS(TRUNC(SYSDATE,'mm'),-1) AND"
						      + " TRUNC(SYSDATE, 'mm') AND"
						      + " emision.sector = '"+sector+"'"
						      + " GROUP BY detalle.usuarioEmisor"
						      + " ORDER BY 3"
						     // + " WHERE ROWNUM <=5;"
						     ).getResultList();
		return results;
	}
}
