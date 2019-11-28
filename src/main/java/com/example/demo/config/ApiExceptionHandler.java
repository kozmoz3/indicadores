package com.example.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class ApiExceptionHandler  extends ResponseEntityExceptionHandler{

	/*@ResponseStatus
	@ExceptionHandler
	@ResponseBody
	public void ex() {
		
	}*/
}
