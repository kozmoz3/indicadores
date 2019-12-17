package com.example.demo.util;

import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.util.*;
import java.time.LocalDate;
import java.time.format.*;

public class DateUtil {
	
	public static String minusMonths(String fecha, long meses) {
        // Crear un formateador como 16/10/2018
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/uu");

        // Lo convertimos a objeto para poder trabajar con él
        LocalDate fechaLocal = LocalDate.parse(fecha, formateador);

        // Restar meses
        fechaLocal = fechaLocal.minusMonths(meses);

        //Formatear de nuevo y regresar como cadena
        return fechaLocal.format(formateador);
    }
	
	public static String plusMonths(String fecha, long meses) {
        // Crear un formateador como 16/10/2018
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/uu");

        // Lo convertimos a objeto para poder trabajar con él
        LocalDate fechaLocal = LocalDate.parse(fecha, formateador);

        // Sumar los meses indicados
        fechaLocal = fechaLocal.plusMonths(meses);

        //Formatear de nuevo y regresar como cadena
        return fechaLocal.format(formateador);
    }

}
