package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.XxmpfBpmIndEmiDetalle;
import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.model.Test1;

@Repository
public interface CMERepository extends JpaRepository<XxmpfBpmIndEmiDetalle, String>{
	
	String querys =" SELECT COUNT(TO_CHAR(detalle.idEmiDetalle)),"
			+ " CASE emision.tipoSolicitud WHEN 'ENDOSO' THEN 'Endosos'"
			+ " ELSE 'Pólizas y renovaciones'"
			+ " END AS TIPO_SOLUCITUD"
            + " FROM XxmpfBpmIndEmiDetalle detalle, XxmpfBpmIndEmision emision"
            + " WHERE emision.idEmision = detalle.idEmisionFK  AND"
            + " detalle.area = 'EmisiÃ³n' AND "
            + " emision.sector = :sector AND"
            + " detalle.fechaInicio BETWEEN :fechaPrev AND :fecha AND"
            + " (detalle.estatus = 'Pendiente' OR detalle.fechaFin >= :fecha)"
            + " GROUP BY CASE emision.tipoSolicitud WHEN 'ENDOSO' THEN 'Endosos' ELSE 'Pólizas y renovaciones' END ";
	
	
	String querys2 =" SELECT count(detalle)"
            + " FROM XxmpfBpmIndEmiDetalle detalle, XxmpfBpmIndEmision emision"
            + " WHERE emision.idEmision = detalle.idEmisionFK  AND"
            + " detalle.area = 'Emisión' AND "
            + " emision.sector = :sector AND"
            + " detalle.fechaInicio BETWEEN :fechaPrev AND :fecha AND"
            + " (detalle.estatus = 'Pendiente' OR detalle.fechaFin >= :fecha)";
	
	@Query(querys2)
	int countDetalle(@Param("sector") String sector,@Param("fechaPrev") String fechaPrev,@Param("fecha") String fecha);
	
	//@Query(querys)
	//List<Test1> findSectorAndFechaInicio(@Param("sector") String sector,@Param("fechaPrev") String fechaPrev,@Param("fecha") String fecha);
	
}
