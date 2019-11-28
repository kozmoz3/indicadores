package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;

@Service("emisionService")
public interface EmisionService {
	
	public List<String> distinctStatusByFechfinAndaSector(String dateStart, String dateFinish, String sector);
	
	public Integer countBySectorAndStatus(List<XxmpfBpmIndEmision> listEmision, String sector, String status);
	
	public List<String> findDistincSectorByFechaFin(String dateStart, String dateFinish);

	public List<String> findDistincSectorByFechaInicio(String dateStart, String dateFinish);
	
	public List<XxmpfBpmIndEmision> findAllEmisionByFechaInicio(String dateStart, String dateFinish);
	
	public List<XxmpfBpmIndEmision> findAllEmisionByFechaFin(String dateStart, String dateFinish);
	
	public Integer countBySector(List<XxmpfBpmIndEmision>  listEmision, String sector);
	
	Integer countSector( List<Object[]> listEmision, String sector, int indexSector);
	
	List<Object []> allByEstatus(String status);
	
}
