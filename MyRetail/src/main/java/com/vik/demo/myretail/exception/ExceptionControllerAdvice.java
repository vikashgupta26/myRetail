package com.vik.demo.myretail.exception;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vik.demo.myretail.util.CustomError;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> getException(@RequestHeader(value = "Authorization") String authentication, Exception e){
		String userBasic = new String(Base64.getDecoder().decode(authentication.split(" ")[1]), StandardCharsets.UTF_8);
		LOGGER.error((userBasic.split(":")[0] + " MERCHANT"),e);
		CustomError customError = new CustomError(userBasic.split(":")[0] + " MERCHANT "+e.getMessage(), e.getCause().getMessage());
		return new ResponseEntity<CustomError>(customError, HttpStatus.BAD_REQUEST);
	}
}
