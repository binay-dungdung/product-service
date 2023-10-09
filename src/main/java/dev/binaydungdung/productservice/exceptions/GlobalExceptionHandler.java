package dev.binaydungdung.productservice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.binaydungdung.productservice.dtos.ErrorDto;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDto> handleNotFoundException(NotFoundException notFoundException) {
		return new ResponseEntity<>(
				new ErrorDto(HttpStatus.NOT_FOUND.name(), notFoundException.getMessage()), 
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ClientException.class)
	public ResponseEntity<ErrorDto> handleClientException(ClientException clientException) {
		return new ResponseEntity<>(
				new ErrorDto(HttpStatus.BAD_REQUEST.name(), clientException.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServerException.class)
	public ResponseEntity<ErrorDto> handleServerException(ServerException serverException) {
		return new ResponseEntity<>(
				new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.name(), serverException.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorDto handleGlobalException(Throwable ex) {
		return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.name(), "An internal error occurred.");
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
															 Object body, HttpHeaders headers, HttpStatusCode statusCode,
															 WebRequest request) {
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode.value());
		return new ResponseEntity<>(new ErrorDto(
				httpStatus.name(), "An internal error occurred."),
				httpStatus);
	}

}
