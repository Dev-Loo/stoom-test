package com.stoom.demo.api.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.stoom.demo.api.advice.exception.CustomNotFoundException;

@RestControllerAdvice
public class AdressServiceAdvice {

	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(CustomNotFoundException ex) {
		ResponseEntity<String> response = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    return response;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
		Map<String, Object> errorMap = new HashMap<String, Object>();
	    e.getBindingResult().getFieldErrors().forEach(fieldError -> {
	    	errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
	    });
	    return new ResponseEntity<Map<String, Object>>(errorMap, HttpStatus.BAD_REQUEST);
	}
}
