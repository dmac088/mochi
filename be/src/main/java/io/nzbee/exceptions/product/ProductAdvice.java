package io.nzbee.exceptions.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ProductAdvice {

	@ResponseBody
	@ExceptionHandler(ProductException.class)
	public final ResponseEntity<ProductNotFoundResponse> categoryNotFoundResponseResponseEntity(ProductException ex) {
		ProductNotFoundResponse response = new ProductNotFoundResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}
