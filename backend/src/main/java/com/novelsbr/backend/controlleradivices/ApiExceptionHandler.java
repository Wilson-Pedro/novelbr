package com.novelsbr.backend.controlleradivices;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.novelsbr.backend.domain.dto.ProblamDTO;
import com.novelsbr.backend.exceptions.EntityNullException;
import com.novelsbr.backend.exceptions.ExistingAuthorException;
import com.novelsbr.backend.exceptions.ExistingNovelException;
import com.novelsbr.backend.exceptions.NotFoundException;
import com.novelsbr.backend.exceptions.TypeNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(ExistingAuthorException.class)
	public ResponseEntity<ProblamDTO> existingAuthorException(ExistingAuthorException e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String title = e.getMessage() != null ? e.getMessage() : "Existing Author";
		
		ProblamDTO problamDTO = new ProblamDTO(title, status.value(), Instant.now());

		return ResponseEntity.status(status).body(problamDTO);
	}
	
	@ExceptionHandler(ExistingNovelException.class)
	public ResponseEntity<ProblamDTO> existingNovelException(ExistingNovelException e) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String title = e.getMessage() != null ? e.getMessage() : "Existing Novel";
		
		ProblamDTO problamDTO = new ProblamDTO(title, status.value(), Instant.now());

		return ResponseEntity.status(status).body(problamDTO);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ProblamDTO> entityNotFoundException(NotFoundException e) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String title = e.getMessage() != null ? e.getMessage() : "Entity not found";
		
		ProblamDTO problamDTO = new ProblamDTO(title, status.value(), Instant.now());

		return ResponseEntity.status(status).body(problamDTO);
	}
	
	@ExceptionHandler(EntityNullException.class)
	public ResponseEntity<ProblamDTO> entityNullException() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ProblamDTO problamDTO = new ProblamDTO("Entity is null", status.value(), Instant.now());

		return ResponseEntity.status(status).body(problamDTO);
	}
	
	@ExceptionHandler(TypeNotFoundException.class)
	public ResponseEntity<ProblamDTO> typeNotFoundException(TypeNotFoundException e) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String title = e.getMessage() != null ? e.getMessage() : "Type not found";
		
		ProblamDTO problamDTO = new ProblamDTO(title, status.value(), Instant.now());

		return ResponseEntity.status(status).body(problamDTO);
	}
}
