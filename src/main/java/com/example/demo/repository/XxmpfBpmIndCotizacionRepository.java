package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.XxmpfBpmIndCotizacion;

@Repository("xxmpfBpmIndCotizacionRepository")
public interface XxmpfBpmIndCotizacionRepository extends JpaRepository<XxmpfBpmIndCotizacion, String>{

	@Query("SELECT DISTINCT cot.agente FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.recotizaciones > 0")
	 List<String> distinctAgenteByRecotizacionAndFechaInicio(String dateStart,String dateFinish);
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.recotizaciones > 0 AND cot.agente = ?3")
	 int countRetrabajoRecotizacionAgenteBySectorAndFechaInicio(String dateStart,String dateFinish, String agente);
	
	@Query("SELECT DISTINCT cot.agente FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.devoluciones > 0")
	 List<String> distinctAgenteByDevolucionesAndFechaInicio(String dateStart,String dateFinish);
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.devoluciones > 0 AND cot.agente = ?3")
	 int countRetrabajoDevolucionAgenteBySectorAndFechaInicio(String dateStart,String dateFinish, String agente);
	
	@Query("SELECT DISTINCT cot.sector FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.recotizaciones > 0")
	 List<String> distinctSectorByRecotizacionAndFechaInicio(String dateStart,String dateFinish);
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.recotizaciones > 0 AND cot.sector = ?3")
	 int countRetrabajoRecotizacionBySectorAndFechaInicio(String dateStart,String dateFinish, String sector);
	
	@Query("SELECT DISTINCT cot.sector FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.devoluciones > 0")
	 List<String> distinctSectorByDevolucionesAndFechaInicio(String dateStart,String dateFinish);
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.devoluciones > 0 AND cot.sector = ?3")
	 int countRetrabajoDevolucionBySectorAndFechaInicio(String dateStart,String dateFinish, String sector);
	
	@Query("SELECT DISTINCT cot.agente FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.status =?3")
	 List<String> findDistinctAgenteByFechaInicio(String dateStart,String dateFinish, String status);
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.status =?3 AND cot.agente = ?4")
	Integer countAgenteByFechaInicioBetween(String dateStart,String dateFinish,String status, String agente );
	
	@Query("SELECT DISTINCT cot.sector FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.status =?3")
	 List<String> findDistinctSectorByFechaInicio(String dateStart,String dateFinish, String status);
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.status =?3 AND cot.sector = ?4")
	Integer countSectorByFechaInicioBetween(String dateStart,String dateFinish,String status, String sector );
	
	@Query("SELECT DISTINCT cot.motivo FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND status LIKE ?3%")
	 List<String> findDistinctMotivoByFechaInicio(String dateStart,String dateFinish, String status);
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.motivo = ?3 AND cot.status LIKE ?4%")
	 Integer countMotivosByFechaInicioBetween(String dateStart,String dateFinish,String motivo, String status );
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot WHERE cot.fechaInicio > ?1 AND cot.fechaInicio < ?2 AND cot.status LIKE ?3%")
	Integer totalMotivosByFechaInicioBetween(String dateStart,String dateFinish,String status );
	
	@Query("SELECT DISTINCT status FROM XxmpfBpmIndCotizacion")
	 List<String> findDistinctEstatus();
	
	@Query("SELECT count(*) FROM XxmpfBpmIndCotizacion")
	 int countRegistreCotizacion();
	
	
	List<XxmpfBpmIndCotizacion> findAllByStatus(String status);
	
	int countByStatus(String status);
	
	@Query("SELECT DISTINCT cot.status FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio >= ?1 and cot.fechaInicio <= ?2")
	 List<String> findDistinctEstatusByBetween(String dateStart,String dateFinish );
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2 and cot.status =?3")
	 int countEstatusByFechaInicioBetween(String dateStart,String dateFinish,String status );
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2 and cot.status is null")
	 int countEstatusIsNullFechaInicioBetween(String dateStart,String dateFinish );
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2")
	 int countEstatusByFechaInicioBetween(String dateStart,String dateFinish );
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2 and cot.recotizaciones > 0")
	 int countRecotizacionByFechaInicioBetween(String dateStart,String dateFinish);
	
	
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2 and cot.devoluciones > 0")
	 int countDevolucionByFechaInicioBetween(String dateStart,String dateFinish);
	
	@Query("SELECT cot FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2")
	 List<XxmpfBpmIndCotizacion> findAllByFechaInicio(String dateStart,String dateFinish);
	
	@Query("SELECT AVG(cot.tiempoDias) FROM XxmpfBpmIndCotizacion cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2")
	 Integer findAVGTiempoDiasByFechaInicio(String dateStart,String dateFinish);
	
}
