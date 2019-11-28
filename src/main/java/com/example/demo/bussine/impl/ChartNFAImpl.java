package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartNFA;
import com.example.demo.controller.IndicadorNFAController;
import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.model.ChartBarraModel;
import com.example.demo.model.DetalleCotizacionModel;
import com.example.demo.service.EmisionService;
import com.example.demo.service.NFAService;

@Service("chartNFAServiceImpl")
public class ChartNFAImpl implements ChartNFA{
	
	private static final Logger logger = LogManager.getLogger(ChartNFAImpl.class);
	
	@Autowired
	@Qualifier("nfaServiceImpl")
	private NFAService nFAService;

	@Override
	public List<ChartBarraModel> drawChart(String dateStart, String dateFinish) {
		List<String> listSector = nFAService.distincSectorByFechaFin(dateStart, dateFinish);
		int sizelist = listSector.size();
		Integer[]data;
		List<ChartBarraModel> listChart = new ArrayList<ChartBarraModel>();
		List<XxmpfBpmIndEmision> listEmision = nFAService.allEmisionByFechaFin(dateStart, dateFinish);
		if(sizelist == 0) {
			data = new Integer[1];
			data[0] = 0;
			listChart.add(new ChartBarraModel("No hay datos", data));
		}else {
			for(int i = 0; i < sizelist; i++) {
				String nameSector = listSector.get(i);
				data = new Integer[1];
				data[0] = nFAService.countBySector(listEmision, nameSector);
				listChart.add(new ChartBarraModel(nameSector, data));
				logger.info("--> Method drawChart add list [ name = "+nameSector+" data = "+data[0]+" ]");
			}
		}
		return listChart;
	}

	@Override
	public List<ChartBarraModel> drawChartStatus(DetalleCotizacionModel detallemodel) {
		
		String sector = detallemodel.getDetalle();
		List<XxmpfBpmIndEmision> listEmision = nFAService.allEmisionByFechaFin(detallemodel.getDateStart(), detallemodel.getDateFinish());
		List<String> listStatus = nFAService.distinctStatusByFechaFinAndSector(detallemodel.getDateStart(), detallemodel.getDateFinish(), sector);
		List<ChartBarraModel> listDetalleStatus = new ArrayList<ChartBarraModel> ();
		for(int i = 0; i<listStatus.size(); i++) {
			String name = listStatus.get(i);
			Integer dato = nFAService.countBySectorAndStatus(listEmision, sector, name);
			Integer [] data = {dato};
			logger.info("--> Method drawChartStatus addlist [ name = "+name+", data = "+dato);
			listDetalleStatus.add(new ChartBarraModel(name, data));
		}
		return listDetalleStatus ;
	}
	
	

}
