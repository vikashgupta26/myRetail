package com.vik.demo.myretail.exception;

import org.springframework.http.HttpStatus;

public class MyRetailProductException extends Exception {

	/**
	 * Auto - generated
	 */
	private static final long serialVersionUID = -1800554614260170166L;
	private final HttpStatus code;
	
	public MyRetailProductException(String message, Throwable cause, HttpStatus code) {
        super(message, cause);
        this.code = code;
    }
	
	public HttpStatus getCode() {
		return this.code;
	}
}
