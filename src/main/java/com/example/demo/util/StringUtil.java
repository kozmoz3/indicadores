package com.example.demo.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.example.demo.controller.CotizacionsController;

public class StringUtil {
	private static final Logger logger = LogManager.getLogger(StringUtil.class);

	public static String containNull(String word) {
		logger.info("method: containNull param = "+word);
		if(word.equals("null") || word == null)
			return "";
		else
			return word;
	}
	
	public static int getHoraByFecha(String fecha) {
		logger.info("method: getHoraByFechal param = "+fecha +" hora: "+fecha.substring(11, 13));
		String hora = fecha.substring(11, 13);
       int hrs = Integer.parseInt(hora.trim());
		return hrs;
	}
	
	public static String getFecha(String fecha) {
		logger.info("method: getFecha param = "+fecha +" hora: "+fecha.substring(0, 10));
		 return fecha.substring(0, 10);   
	}
	
	public static String getHora(String fecha) {
		logger.info("method: getHora param = "+fecha +" hora: "+fecha.substring(11, 16));
		 return fecha.substring(11, 16);
       
	}
}
