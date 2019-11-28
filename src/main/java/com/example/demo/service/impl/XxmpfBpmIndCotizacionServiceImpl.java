package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.service.XxmpfBpmIndCotizacionService;
import com.example.demo.repository.XxmpfBpmIndCotizacionRepository;

@Service("cotizacionImpl")
public class XxmpfBpmIndCotizacionServiceImpl  implements XxmpfBpmIndCotizacionService{

	@Autowired
	private XxmpfBpmIndCotizacionRepository repository;
	
	
	@Override
	public List<String> distinctEstatus(){
		return  repository.findDistinctEstatus();
	}

	@Override
	public int countCotizacion() {
		return repository.countRegistreCotizacion();
	}

	@Override
	public int countRegistreByEstatus(String status) {
	    return repository.countByStatus(status);
	}
	
	@Override
	public List<String> getStatusByBetween(String dateStart,String dateFinish ){
		List<String> listStatus = repository.findDistinctEstatusByBetween(dateStart,dateFinish );
		listStatus.forEach(item->System.out.println("Status = "+item));
		return listStatus;
	}
	
	@Override
	public int countEstatusByFechaInicioBetweens(String dateStart,String dateFinish,String status ){
		if(status == null) {
			return repository.countEstatusIsNullFechaInicioBetween(dateStart, dateFinish);
		}return repository.countEstatusByFechaInicioBetween(dateStart,dateFinish,status );
	}
	
	@Override
	public int countEstatusByFechaInicioBetween(String dateStart,String dateFinish) {
		return repository.countEstatusByFechaInicioBetween(dateStart, dateFinish );
	}
	
	@Override
    public int countRecotizacionByFechaInicioBetween(String dateStart,String dateFinish ) {
		return repository.countRecotizacionByFechaInicioBetween(dateStart, dateFinish);
	}
	
	@Override
	public int countDevolucionByFechaInicioBetween(String dateStart,String dateFinish ) {
		return repository.countDevolucionByFechaInicioBetween(dateStart, dateFinish);
	}
	
	@Override
	public Integer getAVGTiempoDiasByFecaInicio(String dateStart,String dateFinish) {
		return repository.findAVGTiempoDiasByFechaInicio(dateStart, dateFinish);
	}

	@Override
	public List<String> findDistinctMotivoByFechaInicio(String dateStart, String dateFinish, String status) {
		return repository.findDistinctMotivoByFechaInicio(dateStart, dateFinish, status);
	}

	@Override
	public Integer countMotivosByFechaInicioBetween(String dateStart, String dateFinish, String motivo,  String status) {
		return repository.countMotivosByFechaInicioBetween(dateStart, dateFinish,motivo, status);
	}

	@Override
	public Integer totalMotivosByFechaInicioBetween(String dateStart, String dateFinish, String status) {
		// TODO Auto-generated method stub
		return repository.totalMotivosByFechaInicioBetween(dateStart, dateFinish, status);
	}
	
}
