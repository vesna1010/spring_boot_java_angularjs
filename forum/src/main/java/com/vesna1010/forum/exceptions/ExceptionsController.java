package com.vesna1010.forum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {

	@ExceptionHandler
	public ResponseEntity<Object> handler(ResourceNotFoundException e) {

		return new ResponseEntity<Object>(new ErrorDetails(e.getMessage()), HttpStatus.NOT_FOUND);
	}
}
