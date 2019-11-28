package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.XxmpfBpmIndCotDetalle;

@Repository("xxmpfBpmIndCotDetalleRepository")
public interface XxmpfBpmIndCotDetalleRepository extends JpaRepository<XxmpfBpmIndCotDetalle, String >{

	@Query("SELECT AVG(cot.tiempoDias) FROM XxmpfBpmIndCotDetalle cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2 and cot.area=?3")
	 Integer findAVGTiempoDiasByFechaInicio(String dateStart,String dateFinish, String area);
	
	@Query("SELECT DISTINCT cot.area FROM XxmpfBpmIndCotDetalle cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2")
	 List<String> findDistinctAreaByBetween(String dateStart,String dateFinish );
	
	@Query("SELECT  COUNT(*) FROM XxmpfBpmIndCotDetalle cot where cot.fechaInicio > ?1 and cot.fechaInicio < ?2 and cot.area=?3")
	Integer countAreaByFechaInicioBetween(String dateStart,String dateFinish,String area );
}
