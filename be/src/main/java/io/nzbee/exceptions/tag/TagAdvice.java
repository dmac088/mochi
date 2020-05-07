package io.nzbee.exceptions.tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class TagAdvice {

	@ResponseBody
	@ExceptionHandler(TagNotFoundException.class)
	public final ResponseEntity<TagNotFoundResponse> TagNotFoundResponseResponseEntity(TagException ex) {
		TagNotFoundResponse response = new TagNotFoundResponse(ex.getMessage());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}	
}
