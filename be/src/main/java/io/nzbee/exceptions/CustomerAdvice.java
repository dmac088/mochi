package io.nzbee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomerAdvice {

	@ResponseBody
	@ExceptionHandler(CustomerException.class)
	public final ResponseEntity<CustomerNotFoundResponse> CustomerNotFoundResponseResponseEntity(CustomerException ex) {
		CustomerNotFoundResponse response = new CustomerNotFoundResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}
