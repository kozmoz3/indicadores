package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component("cotizacionUtil")
public class CotizacionUtil {

	public String convertEstatus(String status) {
		return status;
	}
	/*public String convertEstatus(String status) {
		//System.out.println("estatus ---> "+status);
		String convertStatus = "";
		
		if(status.equals("")|| status.equals(null)) {
			
			convertStatus = "PENDIENTE";
			
		}
		else if( status == null || status.isEmpty()) {
			
			convertStatus = "PENDIENTE";
			
		}
		else if(status.equals("CANCELACION_MANUAL")) {
			convertStatus = "CANCELACION MANUAL";
		}
		else if(status.equals("RECHAZADO")) {
			convertStatus = "RECHAZADO";
		}
		else if(status.equals("COTIZACION_ACEPTADA")) {
			convertStatus = "COTIZACION ACEPTADA";
		}
			
		else if(status.equals("CIERRE_AUTOMATICO")) {
			convertStatus = "CIERRE AUTOMATICO";
		}
		else if(status.equals("NO_ACEPTADO")) {
			convertStatus = "NO ACEPTADO POR EL CLIENTE";
		}
		else if(status.equals("CANCELACION_AUTOMATICA")) {
			convertStatus = "CANCELACION AUTOMATICA";
		}
		
		return  convertStatus;
	}*/
}
