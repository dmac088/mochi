package io.nzbee.exceptions.promotion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class PromotionAdvice {

	@ResponseBody
	@ExceptionHandler(PromotionException.class)
	public final ResponseEntity<PromotionNotFoundResponse> brandNotFoundResponseResponseEntity(PromotionException ex) {
		PromotionNotFoundResponse response = new PromotionNotFoundResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
}
