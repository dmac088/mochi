package io.nzbee.exceptions.customer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler{

	@ResponseBody
	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> CustomerNotFoundResponseEntity(CustomerNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), 
		          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ResponseBody
	@ExceptionHandler(CustomerAlreadyExistException.class)
	public final ResponseEntity<Object> CustomerAlreadyExistResponseEntity(CustomerAlreadyExistException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), 
		          new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}