package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.XxmpfBpmIndEmision;

@Repository("xxmpfBpmIndEmisionRepository")
public interface XxmpfBpmIndEmisionRepository extends JpaRepository<XxmpfBpmIndEmision, String>{

	@Query("SELECT DISTINCT emision.estatus FROM XxmpfBpmIndEmision emision WHERE emision.fechaFin > :dateStart AND emision.fechaFin < :dateFinish AND emision.sector = :sector")
	 List<String> distinctStatusByFechaFinAndSector(@Param("dateStart") String dateStart, @Param("dateFinish")  String dateFinish, @Param("sector")  String sector );
	
	
	@Query("SELECT DISTINCT emision.sector FROM XxmpfBpmIndEmision emision WHERE emision.fechaFin > :dateStart AND emision.fechaFin < :dateFinish")
	 List<String> distinctSectorByFechaFin(@Param("dateStart") String dateStart, @Param("dateFinish")  String dateFinish );
	
	@Query("SELECT DISTINCT emision.sector FROM XxmpfBpmIndEmision emision WHERE emision.fechaInicio > ?1 AND emision.fechaInicio < ?2")
	 List<String> distinctSectorByFechaInicio(String dateStart,String dateFinish );
	
	@Query("SELECT DISTINCT emision.sector FROM XxmpfBpmIndEmision emision")
	 List<String> distinctSector( );
	
	@Query("SELECT emision FROM XxmpfBpmIndEmision emision WHERE emision.fechaFin > :dateStart AND emision.fechaFin < :dateFinish")
	List<XxmpfBpmIndEmision> findAllEmisionByFechaFin(@Param("dateStart") String dateStart, @Param("dateFinish")  String dateFinish);
	
	@Query("SELECT emision FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle WHERE emision.idEmision = detalle.idEmisionFK  AND detalle.area = 'Emisión' AND emision.fechaFin > :dateStart AND emision.fechaFin < :dateFinish")
	List<XxmpfBpmIndEmision> findAllByAreaAndFechaFin(@Param("dateStart") String dateStart, @Param("dateFinish")  String dateFinish);
	
	@Query("SELECT emision FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle WHERE emision.idEmision = detalle.idEmisionFK  AND detalle.area = 'Emisión' AND emision.estatus = 'Emitido' AND detalle.estatus = 'Emitido' AND emision.fechaFin > :dateStart AND emision.fechaFin < :dateFinish")
	List<XxmpfBpmIndEmision> findAllByAreaAndEstatusAndFechaFin(@Param("dateStart") String dateStart, @Param("dateFinish")  String dateFinish);
	
	@Query("SELECT emision FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle WHERE emision.idEmision = detalle.idEmisionFK  AND detalle.area = 'Emisión' AND detalle.actividadATiempo = 1 AND emision.fechaFin > :dateStart AND emision.fechaFin < :dateFinish")
	List<XxmpfBpmIndEmision> findAllByAreaAndFechaFinAndAtiempo(@Param("dateStart") String dateStart, @Param("dateFinish")  String dateFinish);
	
	//@Query(value ="SELECT * FROM xxmpf_Bpm_Ind_Emision  em WHERE em.fecha_Inicio > :dateStart AND em.fecha_Inicio < :dateFinish" , nativeQuery=true)
	@Query("SELECT emision FROM XxmpfBpmIndEmision emision WHERE emision.fechaInicio > :dateStart AND emision.fechaInicio < :dateFinish")
	List<XxmpfBpmIndEmision> findAllEmisionByFechaInicio(@Param("dateStart") String dateStart, @Param("dateFinish")  String dateFinish);
	
	@Query("SELECT emision FROM XxmpfBpmIndEmision emision, XxmpfBpmIndEmiDetalle detalle WHERE emision.idEmision = detalle.idEmisionFK  AND detalle.area = 'Emisión' AND emision.fechaInicio > :dateStart AND emision.fechaInicio < :dateFinish")
	List<XxmpfBpmIndEmision> findAllByAreaAndFechaInicio(@Param("dateStart") String dateStart, @Param("dateFinish")  String dateFinish);
	
}
