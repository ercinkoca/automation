package com.odeal.automation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiErrorResponse> handleAllExceptions(Exception ex, WebRequest request){
		ApiErrorResponse response = new ApiErrorResponse(500, ex.getMessage());
		return new ResponseEntity<ApiErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ApiErrorResponse> handleBadRequestExceptions(Exception ex, WebRequest request){
		ApiErrorResponse response = new ApiErrorResponse(400, ex.getMessage());
		return new ResponseEntity<ApiErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

}
