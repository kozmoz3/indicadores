package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmiDetalle;
import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.service.NFAService;

@Service("nfaServiceImpl")
public class NFAServiceImpl implements NFAService{
	
	@Autowired
	private EmisionServiceImpl emisionService;

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<String> distincSectorByFechaFin(String dateStart, String dateFinish) {
		List<String> resultList= entityManager.
			      createQuery("SELECT DISTINCT(emision.sector)  "
			      		     + "FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
			      		     + " WHERE emision.idEmision = detalle.idEmisionFK  AND"
			      		     + " detalle.area = 'Emisión' AND"
			      		     + " emision.fechaFin > TO_DATE('"+dateStart+"', 'DD/MM/YY') AND"
			      		     		+ " emision.fechaFin < TO_DATE('"+dateFinish+"', 'DD/MM/YY')" ).getResultList();		    
			  return resultList;
		//return emisionService.findDistincSectorByFechaFin(dateStart, dateFinish);
	}

	@Override
	public List<XxmpfBpmIndEmision> allEmisionByFechaFin(String dateStart, String dateFinish) {  
		
		/*List<Object[]> results = entityManager.createQuery("SELECT emision.idEmision, detalle.area, detalle.motivo  FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle WHERE emision.idEmision = detalle.idEmisionFK  AND detalle.area = 'EMISION' AND emision.fechaFin > TO_DATE('"+dateStart+"', 'DD/MM/YY') AND emision.fechaFin < TO_DATE('"+dateFinish+"', 'DD/MM/YY')").getResultList();

		for (Object[] result : results) {
			System.out.println(result[0] + " " + result[1] + " - " + result[2]);
		}*/
		TypedQuery<XxmpfBpmIndEmision> query = entityManager.
			      createQuery("SELECT emision  "
			      		+ "FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle "
			      		+ "WHERE emision.idEmision = detalle.idEmisionFK  AND"
			      		+ " detalle.area = 'Emisión' AND"
			      		+ " emision.fechaFin > TO_DATE('"+dateStart+"', 'DD/MM/YY') AND emision.fechaFin < TO_DATE('"+dateFinish+"', 'DD/MM/YY')", XxmpfBpmIndEmision.class);
			    List<XxmpfBpmIndEmision> resultList = query.getResultList();
			    return resultList;
	//	return emisionService.findAllEmisionByFechaFin(dateStart, dateFinish);
	}
	
	@Override
	public List<Object[]> allEmisionAndDetalleByFechaFin(String dateStart, String dateFinish) {
		List<Object[]> results = entityManager.
				createQuery("SELECT emision.folio, emision.sector, emision.tipoSolicitud, emision.divisional, emision.regional, "
						          + "emision.oficinaComercial, emision.agente, emision.estatus, emision.motivo, "
						          + "detalle.usuarioAnalista ,detalle.usuarioEmisor, detalle.usuarioSuscriptor, emision.fechaFin"
						+ " FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND"
						      + " detalle.area = 'Emisión' AND "
						      + "emision.fechaFin > TO_DATE('"+dateStart+"', 'DD/MM/YY') AND "
						      + "emision.fechaFin < TO_DATE('"+dateFinish+"', 'DD/MM/YY')").getResultList();
		return results;
	}

	@Override
	public Integer countBySector(List<XxmpfBpmIndEmision> listEmision, String sector) {
		// TODO Auto-generated method stub
		return emisionService.countBySector(listEmision, sector);
	}

	@Override
	public List<String> distinctStatusByFechaFinAndSector(String dateStart, String dateFinish, String sector) {
		// TODO Auto-generated method stub
		return emisionService.distinctStatusByFechfinAndaSector(dateStart, dateFinish, sector);
	}

	@Override
	public Integer countBySectorAndStatus(List<XxmpfBpmIndEmision> listEmision, String sector, String status) {
		// TODO Auto-generated method stub
		return emisionService.countBySectorAndStatus(listEmision, sector, status);
	}

}
