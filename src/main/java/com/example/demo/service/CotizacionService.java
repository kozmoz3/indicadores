package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface CotizacionService {

	List<String> distinctAgenteByRecotizacionAndFechaInicio(String dateStart, String dateFinish);

	int countRetrabajoRecotizacionAgenteBySectorAndFechaInicio(String dateStart, String dateFinish, String agente);

	List<String> distinctAgenteByDevolucionesAndFechaInicio(String dateStart, String dateFinish);

	int countRetrabajoDevolucionAgenteBySectorAndFechaInicio(String dateStart, String dateFinish, String agente);

	List<String> distinctSectorByRecotizacionAndFechaInicio(String dateStart, String dateFinish);

	int countRetrabajoRecotizacionBySectorAndFechaInicio(String dateStart, String dateFinish, String sector);

	List<String> distinctSectorByDevolucionesAndFechaInicio(String dateStart, String dateFinish);

	int countRecotizacionBySectorAndFechaInicio(String dateStart, String dateFinish, String sector);

	List<String> findDistinctSectorByFechaInicio(String dateStart, String dateFinish, String status);

	Integer countSectorByFechaInicioBetween(String dateStart, String dateFinish, String status, String sector);

	List<String> findDistinctAgenteByFechaInicio(String dateStart, String dateFinish, String status);

	Integer countAgenteByFechaInicioBetween(String dateStart, String dateFinish, String status, String agente);
}
