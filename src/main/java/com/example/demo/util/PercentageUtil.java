package com.example.demo.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PercentageUtil {

	private static final Logger logger = LogManager.getLogger(PercentageUtil.class);
	
	
	public String porcentaje(int numTotal, int numDatos) {
		logger.info("porcentaje param [Total de registros = "+numTotal +" numero de datos = "+ numDatos+" ]");	
		if(numDatos == 0 || numTotal == 0)return "0";
		return  porcentajeBigdecimal(numTotal, numDatos)+"";
	}
	
	public BigDecimal porcentajeBigdecimal(int numTotal, int numDatos) {
		logger.info("porcentaje param [Total de registros = "+numTotal +" numero de datos = "+ numDatos+" ]");	
		if(numDatos == 0 || numTotal == 0) {
			return new BigDecimal("0");
		}else {
		BigDecimal ciento = new BigDecimal("100");
		BigDecimal porNumDatos = new BigDecimal(numDatos+"");
		BigDecimal multResult = ciento.multiply(porNumDatos);
		BigDecimal porNumTotal = new BigDecimal(numTotal+"");
		BigDecimal divResult = multResult.divide(porNumTotal, MathContext.DECIMAL32);
		return  divResult;
		}
	}
	
}
