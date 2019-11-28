package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DateModel;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.FPPTService;
import com.example.demo.service.FPRService;

@Service
public class FPRServiceImpl  implements FPRService{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private XxmpfBpmIndEmisionRepository repository;
	
	@Override
	public List<Object []> allFolioRecibidoByFechaInicio(DateModel date){
		List<Object[]> results = entityManager.
				createQuery("SELECT emision.fechaFin, detalle.usuarioEmisor, emision.sector, emision.folio"
						+ " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND"
						      + " detalle.area = 'Emisión' AND "
						      + " detalle.estatus != 'EN_PROCESO' AND"
						      + " emision.estatus != 'EN_PROCESO' AND"
						      + " emision.fechaInicio  >= TO_DATE('"+date.getDateStart()+"', 'DD/MM/YY') AND"
						      + " emision.fechaInicio  <= TO_DATE('"+date.getDateFinish()+"', 'DD/MM/YY') "
						     ).getResultList();
		return results;
	}
	
	@Override
	public List<Object []> allFolioAtendidoByFechaFin(DateModel date){
		List<Object[]> results = entityManager.
				createQuery("SELECT emision.fechaFin, detalle.usuarioEmisor, emision.sector, emision.folio"
						+ " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND"
						      + " detalle.area = 'Emisión' AND "
						      + " detalle.estatus != 'EN_PROCESO' AND"
						      + " emision.estatus != 'EN_PROCESO' AND"
						      + " emision.fechaFin  >= TO_DATE('"+date.getDateStart()+"', 'DD/MM/YY') AND"
						      + " emision.fechaFin  <= TO_DATE('"+date.getDateFinish()+"', 'DD/MM/YY') "
						     ).getResultList();
		return results;
	}
	
	@Override
	public List<String> getSectores(){
		return repository.distinctSector();
	}
}
