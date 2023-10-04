package dev.binaydungdung.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.binaydungdung.productservice.dtos.ErrorDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException notFoundException) {
		return new ResponseEntity<ErrorDto>(
				new ErrorDto(HttpStatus.NOT_FOUND.name(), notFoundException.getMessage()), 
				HttpStatus.NOT_FOUND);
	}
}
