package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.XxmpfBpmIndEmision;

@Service("nfaService")
public interface NFAService {

	public List<String> distinctStatusByFechaFinAndSector(String dateStart, String dateFinish, String sector);

	public Integer countBySectorAndStatus(List<XxmpfBpmIndEmision> listEmision, String sector, String status);

	public List<String> distincSectorByFechaFin(String dateStart, String dateFinish);

	public List<XxmpfBpmIndEmision> allEmisionByFechaFin(String dateStart, String dateFinish);

	public Integer countBySector(List<XxmpfBpmIndEmision> listEmision, String sector);
	
	public List<Object[]> allEmisionAndDetalleByFechaFin(String dateStart, String dateFinish);
}
