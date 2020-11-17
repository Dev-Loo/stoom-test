package com.stoom.demo.api.advice.exception;

public class CustomNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CustomNotFoundException(String msg) {
	    super(msg);
	}

}
