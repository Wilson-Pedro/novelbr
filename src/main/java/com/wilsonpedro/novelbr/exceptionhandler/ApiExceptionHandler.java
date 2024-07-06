package com.wilsonpedro.novelbr.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EntityNotFoundException;
import com.wilsonpedro.novelbr.exceptionhandler.problam.Problam;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Problam> entityNotFoundException() {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problam problam = new Problam();
		problam.setTitle("Entity Not Found");
		problam.setStatusCode(status.value());
		problam.setTime(OffsetDateTime.now());
		
		return ResponseEntity.status(status).body(problam);
	}
}
