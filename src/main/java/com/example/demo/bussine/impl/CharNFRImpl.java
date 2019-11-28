package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartNFR;
import com.example.demo.entity.XxmpfBpmIndEmision;
import com.example.demo.model.ChartBarraModel;
import com.example.demo.service.NFRService;

@Service("chartNFRImpl")
public class CharNFRImpl implements ChartNFR {

	private static final Logger logger = LogManager.getLogger(CharNFRImpl.class);

	@Autowired
	@Qualifier("nfrServiceImpl")
	private NFRService nfrService;

	@Override
	public List<ChartBarraModel> drawChartBarraSimple(String dateStart, String dateFinish) {
		List<String> listSector = nfrService.findSectorByFechaInicio(dateStart, dateFinish);
		int lengtList = listSector.size();
		List<ChartBarraModel> listChart = new ArrayList<ChartBarraModel>();

		List<XxmpfBpmIndEmision> listEmision = nfrService.findAllEmisionByFechaInicio(dateStart, dateFinish);
		if (lengtList == 0) {
			Integer[] arrayData = new Integer[1];
			arrayData[0] = 0;
			listChart.add(new ChartBarraModel("No hay datos", arrayData));
		} else {
			for (int i = 0; i < lengtList; i++) {
				Integer data = nfrService.countBySectorAndFechaInicioBetween(listEmision, listSector.get(i));
				Integer[] arrayData = new Integer[1];
				arrayData[0] = data;
				logger.info("Method drawChartBarraSimple chartBarra param[ name = " + listSector.get(i) + " data = "
						+ data + " ]");
				listChart.add(new ChartBarraModel(listSector.get(i), arrayData));
			}
		}

		return listChart;
	}

}
