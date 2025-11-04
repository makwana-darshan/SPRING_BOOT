package com.flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flight.dto.ResponseStructure;

@ControllerAdvice
public class GlobleExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("failure");
		response.setData(exception.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoRecordAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleNoRecordAvailableException(
			NoRecordAvailableException exception) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("record not available");
		response.setData(exception.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.NOT_FOUND);
	}
}
