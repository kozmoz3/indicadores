package com.example.demo.exceptions;

public class NotValidateDateException extends Exception {

	public static final String DESCRIPTION = "EL DATO FECHA NO TIENE EL FORMATO CORRECTO";
	
	public static final int CODE = 333;
	
	public NotValidateDateException() {
		this("");
	}
	
	public NotValidateDateException(String detail) {
		super( DESCRIPTION +". "+detail+". "+CODE);
	}
}
