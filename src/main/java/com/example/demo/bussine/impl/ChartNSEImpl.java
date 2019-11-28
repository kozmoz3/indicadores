package com.example.demo.bussine.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartNSE;
import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.model.ChartBarraPorcentajeModel;
import com.example.demo.model.DateModel;
import com.example.demo.service.EmisionService;
import com.example.demo.service.NSEService;
import com.example.demo.util.PercentageUtil;

@Service
public class ChartNSEImpl implements ChartNSE{
	
	@Autowired
	NSEService service;
	
	@Autowired
	EmisionService emision;
	
	@Autowired
	PercentageUtil percentageUtil;
	
	public List<ChartBarraPorcentajeModel> chartFilterFecha(DateModel datemodel){
		List<ChartBarraPorcentajeModel>listChart  = new ArrayList<ChartBarraPorcentajeModel>();
		List<String> listSectores = service.findAllSectores();
		List<XxmpfBpmIndEmision>listAtendidos = service.findAllAtendidos(datemodel.getDateStart(), datemodel.getDateFinish());
		List<XxmpfBpmIndEmision> listRecibidos = service.findAllRecibidos(datemodel.getDateStart(), datemodel.getDateFinish());
		List<XxmpfBpmIndEmision> listATiempo = service.findAllAtiempo(datemodel.getDateStart(), datemodel.getDateFinish());
		List<BigDecimal>listAtencion = new ArrayList<BigDecimal>();
		List<BigDecimal>listServicio = new ArrayList<BigDecimal>();
		
		for(int i = 0; i < listSectores.size(); i++) {
			listAtencion.add(porcentajeAtendidos(listAtendidos, listSectores.get(i), listRecibidos));
			listServicio.add(porcentajeAtendidos(listATiempo, listSectores.get(i), listAtendidos));
		}
		listChart.add(new ChartBarraPorcentajeModel(listSectores, "Nivel de atención", listAtencion));
		listChart.add(new ChartBarraPorcentajeModel(listSectores, "Nivel de servicio", listServicio));
		
		return listChart;
	}

	private BigDecimal porcentajeAtendidos( List<XxmpfBpmIndEmision>listAtendidos, String sector,List<XxmpfBpmIndEmision> listRecibidos ) {
		
		int countAtendidos = emision.countBySector(listAtendidos, sector);
		int countRecibidos = emision.countBySector(listRecibidos, sector);
		return percentageUtil.porcentajeBigdecimal(countRecibidos, countAtendidos);
	}
}
