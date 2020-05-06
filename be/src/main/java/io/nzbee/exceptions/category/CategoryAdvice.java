package io.nzbee.exceptions.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CategoryAdvice {

	@ResponseBody
	@ExceptionHandler(CategoryException.class)
	public final ResponseEntity<CategoryNotFoundResponse> categoryNotFoundResponseResponseEntity(CategoryException ex) {
		CategoryNotFoundResponse response = new CategoryNotFoundResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}
