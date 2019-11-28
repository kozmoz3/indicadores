package com.example.demo.bussine.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bussine.ChartFPR;
import com.example.demo.model.DateModel;
import com.example.demo.model.FPRModel;
import com.example.demo.service.FPRService;
import com.example.demo.util.StringUtil;

@Service
public class ChartFPRImpl implements ChartFPR {
	
	private static final Logger logger = LogManager.getLogger(ChartFPRImpl.class);

	private String[] rangoHoras = { "08:00 - 08:59", "09:00 - 09:59", "10:00 - 10:59", "11:00 - 11:59", "12:00 - 12:59",
			"13:00 - 13:59", "14:00 - 14:59", "15:00 - 15:59", "16:00 - 16:59", "17:00 - 18:00", };

	private int[] horario = { 8, 9, 10, 11, 12, 13, 14, 15, 16, 17 };

	@Autowired
	private FPRService fprService;
	
	
	@Override
	public List<FPRModel> create(DateModel date) {
		List<FPRModel> listTable = new ArrayList<FPRModel>();
		List<String> listSectores = fprService.getSectores();
		List<Object []> listRecibidos = fprService.allFolioRecibidoByFechaInicio(date);
		List<Object []> listAtendidos = fprService.allFolioAtendidoByFechaFin(date);
		
		for (int j = 0; j < listSectores.size(); j++) {
			for (int i = 0; i < rangoHoras.length; i++) {
				String sector = listSectores.get(j);
				String hora = rangoHoras[i];
				int recibidos = count(listRecibidos,horario[i],sector);
				int atendido = count(listAtendidos,horario[i],sector);
				logger.info("method-: create add [ sector = "+sector +" hora = "+hora +" recibidos = "+recibidos+" atendidos = "+atendido);
				listTable.add(new FPRModel(sector, hora, recibidos,atendido ));
				sector ="";
				hora = "";
			}
		}
		return listTable;
	}
	
	private int count( List<Object []> folios, int hora, String sector) {
		int count = 0;
		for (int i = 0; i <  folios.size(); i++) {
			
			if(StringUtil.getHoraByFecha(folios.get(i)[0].toString())== hora && folios.get(i)[2].toString().equals(sector)) {
				logger.info("method-: count param [ "+folios.get(i)[0].toString()+ " hora = "+hora+ " sector"+folios.get(i)[2].toString() + " == "+sector);
				count++;
			}
		}
		return count;
	}
	
	
}
