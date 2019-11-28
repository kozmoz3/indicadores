package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.XxmpfBpmIndCotizacionRepository;
import com.example.demo.service.CotizacionService;

@Service("cotizacionServiceImpl")
public class CotizacionServiceImpl implements CotizacionService {

	@Autowired
	private XxmpfBpmIndCotizacionRepository repository;
	
	@Override
	public List<String> findDistinctSectorByFechaInicio(String dateStart, String dateFinish, String status) {
		return repository.findDistinctSectorByFechaInicio(dateStart, dateFinish, status);
	}

	@Override
	public Integer countSectorByFechaInicioBetween(String dateStart, String dateFinish, String status, String sector) {
		return repository.countSectorByFechaInicioBetween(dateStart, dateFinish,status, sector);
	}

	@Override
	public List<String> findDistinctAgenteByFechaInicio(String dateStart, String dateFinish, String status) {
		// TODO Auto-generated method stub
		return repository.findDistinctAgenteByFechaInicio(dateStart, dateFinish, status);
	}

	@Override
	public Integer countAgenteByFechaInicioBetween(String dateStart, String dateFinish, String status, String agente) {
		// TODO Auto-generated method stub
		return repository.countAgenteByFechaInicioBetween(dateStart, dateFinish, status, agente);
	}

	@Override
	public List<String> distinctSectorByDevolucionesAndFechaInicio(String dateStart, String dateFinish) {
		// TODO Auto-generated method stub
		return repository.distinctSectorByDevolucionesAndFechaInicio(dateStart, dateFinish);
	}

	@Override
	public int countRecotizacionBySectorAndFechaInicio(String dateStart, String dateFinish, String sector) {
		return repository.countRetrabajoDevolucionBySectorAndFechaInicio(dateStart, dateFinish, sector);
	}

	@Override
	public List<String> distinctSectorByRecotizacionAndFechaInicio(String dateStart, String dateFinish) {
		// TODO Auto-generated method stub
		return repository.distinctSectorByRecotizacionAndFechaInicio(dateStart, dateFinish);
	}

	@Override
	public int countRetrabajoRecotizacionBySectorAndFechaInicio(String dateStart, String dateFinish, String sector) {
		// TODO Auto-generated method stub
		return repository.countRetrabajoRecotizacionBySectorAndFechaInicio(dateStart, dateFinish, sector);
	}

	@Override
	public List<String> distinctAgenteByRecotizacionAndFechaInicio(String dateStart, String dateFinish) {
		// TODO Auto-generated method stub
		return repository.distinctAgenteByRecotizacionAndFechaInicio(dateStart, dateFinish);
	}

	@Override
	public int countRetrabajoRecotizacionAgenteBySectorAndFechaInicio(String dateStart, String dateFinish,
			String agente) {
		// TODO Auto-generated method stub
		return repository.countRetrabajoRecotizacionAgenteBySectorAndFechaInicio(dateStart, dateFinish, agente);
	}

	@Override
	public List<String> distinctAgenteByDevolucionesAndFechaInicio(String dateStart, String dateFinish) {
		// TODO Auto-generated method stub
		return repository.distinctAgenteByDevolucionesAndFechaInicio(dateStart, dateFinish);
	}

	@Override
	public int countRetrabajoDevolucionAgenteBySectorAndFechaInicio(String dateStart, String dateFinish,
			String agente) {
		// TODO Auto-generated method stub
		return repository.countRetrabajoDevolucionAgenteBySectorAndFechaInicio(dateStart, dateFinish, agente);
	}

}
