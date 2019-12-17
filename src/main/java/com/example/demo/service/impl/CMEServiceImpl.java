package com.example.demo.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.demo.model.DateModel;
import com.example.demo.pdf.NFAPdf;
import com.example.demo.repository.XxmpfBpmIndEmisionRepository;
import com.example.demo.service.CMEService;
import com.example.demo.util.DateUtil;

@Service
public class CMEServiceImpl implements CMEService {

	private static final Logger logger = LogManager.getLogger(CMEServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	XxmpfBpmIndEmisionRepository emisionRepository;

	@Override
	public List<String> sectores() {
		return emisionRepository.distinctSector();
	}

	@Override
	public List<Object[]> countDetalle(String sector, String fecha) {
		String dateComplit = "01/" + fecha;
		String prevDate = DateUtil.minusMonths(dateComplit, 1);

		List<Object[]> results = entityManager.createQuery(
				" SELECT COUNT(emision)" + " FROM XxmpfBpmIndEmiDetalle detalle, XxmpfBpmIndEmision emision"
						+ " WHERE emision.idEmision = detalle.idEmisionFK  AND" + " detalle.area = 'Emisión' AND "
						+ " emision.sector = '" + sector + "' AND" + " detalle.fechaInicio BETWEEN TO_DATE('" + prevDate
						+ "', 'DD/MM/YY') AND TO_DATE('" + dateComplit + "', 'DD/MM/YY') AND"
						+ " (detalle.estatus = 'Pendiente' OR detalle.fechaFin >= TO_DATE('" + dateComplit
						+ "', 'DD/MM/YY'))" + "")
				.getResultList();
		return results;
	}

	@Override
	public List<Object[]> foliosRegistre(String sector, String fecha) {
		String dateComplit = "01/" + fecha;
		String prevDate = DateUtil.minusMonths(dateComplit, 1);

		List<Object[]> results = entityManager.createQuery(" SELECT COUNT(detalle),"
				+ " CASE emision.tipoSolicitud WHEN 'ENDOSO' THEN 'Endosos'" + " ELSE 'Pólizas y renovaciones'"
				+ " END " + " FROM XxmpfBpmIndEmiDetalle detalle, XxmpfBpmIndEmision emision"
				+ " WHERE emision.idEmision = detalle.idEmisionFK  AND" + " detalle.area = 'Emisión' AND "
				+ " emision.sector = '" + sector + "' AND" + " detalle.fechaInicio BETWEEN TO_DATE('" + prevDate
				+ "', 'DD/MM/YY') AND TO_DATE('" + dateComplit + "', 'DD/MM/YY') AND"
				+ " (detalle.estatus = 'Pendiente' OR detalle.fechaFin >= TO_DATE('" + dateComplit + "', 'DD/MM/YY'))"
				+ " GROUP BY CASE emision.tipoSolicitud WHEN 'ENDOSO' THEN 'Endosos' "
				+ " ELSE 'Pólizas y renovaciones' END").getResultList();

		for (Object[] emision : results) {
			logger.info("Method: foliosRegistre add[ COUNT= " + emision[0].toString() + ", Tipo de Solicitud = "
					+ emision[1].toString());

		}

		return results;
	}

	@Override
	public List<Object[]> getPolizasAndEndosos(String sector, String fecha) {
		String dateComplit = "01/" + fecha;
		String prevDate = DateUtil.minusMonths(dateComplit, 1);

		List<Object[]> results = entityManager.createQuery(" SELECT COUNT(TO_CHAR(detalle.idEmiDetalle)),"
				+ "  TO_CHAR(detalle.fechaInicio, 'dd/MM/yy'), " + "  detalle.fechaInicio,"
				+ " CASE emision.tipoSolicitud WHEN 'ENDOSO' THEN 'Endosos'" + " ELSE 'Pólizas y renovaciones'"
				+ " END " + " FROM XxmpfBpmIndEmiDetalle detalle, XxmpfBpmIndEmision emision"
				+ " WHERE emision.idEmision = detalle.idEmisionFK  AND" + " detalle.area = 'Emisión' AND "
				+ " emision.sector = '" + sector + "' AND" + " detalle.fechaInicio BETWEEN TO_DATE('" + prevDate
				+ "', 'DD/MM/YY') AND TO_DATE('" + dateComplit + "', 'DD/MM/YY') AND"
				+ " (detalle.estatus = 'Pendiente' OR detalle.fechaFin >= TO_DATE('" + dateComplit + "', 'DD/MM/YY'))"
				+ " GROUP By TO CHAR(detalle.fechaInicio, 'dd/MM/yy')," + " CASE emision.tipoSolicitud"
				+ " WHEN 'ENDOSO' THEN 'Endosos' " + " ELSE 'Pólizas y renovaciones' END," + " detalle.fechaInicio"
				+ " ORDER BY detalle.fechaInicio ").getResultList();

		for (Object[] emision : results) {
			logger.info("Method: getPolizasAndEndosos add[ Count = " + emision[0].toString() + ", Fecha Inicio = "
					+ emision[1].toString() + ", Tipo de solicitud = " + emision[3].toString());

		}

		return results;
	}

	@Override
	public List<Object[]> getDetalleFolio(String sector, String fecha) {
		String dateComplit = "01/" + fecha;
		String prevDate = DateUtil.minusMonths(dateComplit, 1);

		List<Object[]> results = entityManager.createQuery(" SELECT COUNT(folio),"
				+ "  CASE estatus WHEN 'Pendiente' THEN 'Folios pendientes'" + " ELSE 'Folios Terminados' END"
				+ " FROM  XxmpfBpmIndEmision " + " WHERE  emision.sector = '" + sector + "' AND"
				+ " detalle.fechaInicio BETWEEN TO_DATE('" + prevDate + "', 'DD/MM/YY') AND TO_DATE('" + dateComplit
				+ "', 'DD/MM/YY') " + " GROUP BY CASE estatus WHEN 'Pendiente' THEN 'Folios pendientes'"
				+ " ELSE 'Folios Terminados' END" + " ORDER BY 2").getResultList();

		for (Object[] emision : results) {
			logger.info("Method: getDetalleFolio add[ Count = " + emision[0].toString() + ", CASE = "
					+ emision[1].toString());

		}

		return results;
	}

	@Override
	public List<Object[]> detalleFoliosPendientes(String sector, String fecha) {
		String dateComplit = "01/" + fecha;
		String prevDate = DateUtil.minusMonths(dateComplit, 1);

		List<Object[]> results = entityManager.createQuery(" SELECT COUNT(detalle)," + " detalle.area,"
				+ " detalle.actividad" + " FROM XxmpfBpmIndEmiDetalle detalle, XxmpfBpmIndEmision emision"
				+ " WHERE emision.idEmision = detalle.idEmisionFK  AND" + " detalle.estatus = 'Pendiente' AND"
				+ " emision.sector = '" + sector + "' AND" + " detalle.fechaInicio BETWEEN TO_DATE('" + prevDate
				+ "', 'DD/MM/YY') AND TO_DATE('" + dateComplit + "', 'DD/MM/YY')"
				+ " GROUP BY detalle.area, detalle.actividad").getResultList();

		for (Object[] emision : results) {
			logger.info("Method: detalleFoliosPendientes add[ COUNT = " + emision[0].toString() + ", Area = "
					+ emision[1].toString() + ", Actividad = " + emision[2].toString());

		}

		return results;
	}

	@Override
	public List<Object[]> excelFoliosPendientes(String sector, String fecha) {
		String dateComplit = "01/" + fecha;
		String prevDate = DateUtil.minusMonths(dateComplit, 1);

		List<Object[]> results = entityManager.createQuery(" SELECT emision.folio," + " detalle.idEmiDetalle,"
				+ " CASE gen.tipo_solicitud WHEN 'ENDOSO' THEN 'Endosos'" + "      ELSE 'Pólizas y renovaciones'"
				+ "      END AS TIPO_SOLUCITUD," + " detalle.area," + " detalle.actividad," + " emision.divisional,"
				+ " emision.regional," + " emision.oficinaComercial," + " detalle.usuarioAnalista,"
				+ " detalle.usuarioEmisor," + " detalle.nombreEmisor," + " detalle.usuarioSuscriptor,"
				+ " detalle.nombreSuscriptor," + " emision.FechaInicio," + " emision.tiempoDias"
				+ " FROM XxmpfBpmIndEmiDetalle detalle, XxmpfBpmIndEmision emision"
				+ " WHERE emision.idEmision = detalle.idEmisionFK  AND" + " detalle.estatus = 'Pendiente' AND"
				+ " emision.sector = '" + sector + "' AND" + " detalle.fechaInicio BETWEEN TO_DATE('" + prevDate
				+ "', 'DD/MM/YY') AND TO_DATE('" + dateComplit + "', 'DD/MM/YY')" + " GROUP BY 1,2").getResultList();

		for (Object[] emision : results) {
			logger.info("Method: excelFoliosPendientes add[ Folio = " + emision[0].toString() + ", Tipo de solicitud = "
					+ emision[1].toString() + ", Area = " + emision[2].toString());

		}

		return results;
	}

	@Override
	public List<Object[]> excelComplit(String sector, String fecha) {
		String dateComplit = "01/" + fecha;
		String prevDate = DateUtil.minusMonths(dateComplit, 1);

		List<Object[]> results = entityManager.createQuery(" SELECT emision.folio," + " detalle.idEmiDetalle,"
				+ " CASE gen.tipo_solicitud WHEN 'ENDOSO' THEN 'Endosos'" + "      ELSE 'Pólizas y renovaciones'"
				+ "      END AS TIPO_SOLUCITUD," + " detalle.area," + " detalle.actividad," + " emision.divisional,"
				+ " emision.regional," + " emision.oficinaComercial," + " detalle.usuarioAnalista,"
				+ " detalle.usuarioEmisor," + " detalle.nombreEmisor," + " detalle.usuarioSuscriptor,"
				+ " detalle.nombreSuscriptor," + " emision.FechaInicio," + " emision.tiempoDias"
				+ " FROM XxmpfBpmIndEmiDetalle detalle, XxmpfBpmIndEmision emision"
				+ " WHERE emision.idEmision = detalle.idEmisionFK  AND" + " detalle.estatus != 'Pendiente' AND"
				+ " emision.sector = '" + sector + "' AND" + " detalle.fechaInicio BETWEEN TO_DATE('" + prevDate
				+ "', 'DD/MM/YY') AND TO_DATE('" + dateComplit + "', 'DD/MM/YY')" + " GROUP BY 1,2").getResultList();

		for (Object[] emision : results) {
			logger.info("Method: excelFoliosPendientes add[ Folio = " + emision[0].toString() + ", Tipo de solicitud = "
					+ emision[1].toString() + ", Area = " + emision[2].toString());

		}

		return results;
	}

}
