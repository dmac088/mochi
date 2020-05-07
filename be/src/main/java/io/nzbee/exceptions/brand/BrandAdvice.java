package io.nzbee.exceptions.brand;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BrandAdvice {

	@ResponseBody
	@ExceptionHandler(BrandException.class)
	public final ResponseEntity<BrandNotFoundResponse> brandNotFoundResponseResponseEntity(BrandException ex) {
		BrandNotFoundResponse response = new BrandNotFoundResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}
