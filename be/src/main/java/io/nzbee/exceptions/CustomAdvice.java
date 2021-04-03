package io.nzbee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomAdvice {

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<CustomResponse> notFoundResponseResponseEntity(NotFoundException ex) {
		CustomResponse response = new CustomResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}
