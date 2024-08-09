package com.nidhi.ThirdPartyIntegration.Exceptions;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Map<String, Object> errorMap;
	Logger log = LoggerFactory.getLogger(Logger.class);
	PrintWriter pw = new PrintWriter(System.out);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public Map<String, Object> resourceNotFoundExceptionHandler(HttpClientErrorException ex) {
		errorMap = new HashMap<String, Object>();
		errorMap.put("message", ex.getMessage());
		log.error(ex.getMessage());
		ex.printStackTrace();
		return errorMap;
	}
	
	public Map<String,Object> exceptionHandler(Exception ex){
		errorMap = new HashMap<String, Object>();
		errorMap.put("Message", ex.getMessage());	
		log.error(ex.getMessage());
		ex.printStackTrace();
		return errorMap;
	}
}
