package com.wilsonpedro.novelbr.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EmailExistsException;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EntityNotFoundException;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.PseudonymExistsException;
import com.wilsonpedro.novelbr.exceptionhandler.problam.Field;
import com.wilsonpedro.novelbr.exceptionhandler.problam.Problam;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<Field> fields = errorFields(ex, messageSource);
		
		Problam problam = new Problam();
		problam.setTitle("One or more field are invalids. Please fill them  in correctly!");
		problam.setStatusCode(status.value());
		problam.setTime(OffsetDateTime.now());
		problam.setFields(fields);
		
		return handleExceptionInternal(ex, problam, headers, status, request);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Problam> entityNotFoundException() {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		Problam problam = new Problam();
		problam.setTitle("Entity Not Found");
		problam.setStatusCode(status.value());
		problam.setTime(OffsetDateTime.now());
		
		return ResponseEntity.status(status).body(problam);
	}
	
	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<Problam> emailExistsException() {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problam problam = new Problam();
		problam.setTitle("Email Exists!");
		problam.setStatusCode(status.value());
		problam.setTime(OffsetDateTime.now());
		
		return ResponseEntity.status(status).body(problam);
	}
	
	@ExceptionHandler(PseudonymExistsException.class)
	public ResponseEntity<Problam> pseudonymExistsException() {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problam problam = new Problam();
		problam.setTitle("Pseudonym Exists!");
		problam.setStatusCode(status.value());
		problam.setTime(OffsetDateTime.now());
		
		return ResponseEntity.status(status).body(problam);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Problam> dataIntegrityViolationException() {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problam problam = new Problam();
		problam.setTitle("You cannot delete this Entity because it is related to another Entity.");
		problam.setStatusCode(status.value());
		problam.setTime(OffsetDateTime.now());
		
		return ResponseEntity.status(status).body(problam);
	}
	
	private List<Field> errorFields(MethodArgumentNotValidException ex, MessageSource messageSource) {
		List<Field> fields = new ArrayList<>();
		
		for(ObjectError error: ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError)error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new Field(name, message));
		}
		
		return fields;
	}
}
