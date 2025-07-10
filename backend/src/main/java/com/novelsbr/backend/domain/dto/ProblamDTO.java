package com.novelsbr.backend.domain.dto;

import java.io.Serializable;
import java.time.Instant;

public class ProblamDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Integer errorCod;
	private Instant instant;
	
	public ProblamDTO() {
	}

	public ProblamDTO(String message, Integer errorCod, Instant instant) {
		this.message = message;
		this.errorCod = errorCod;
		this.instant = instant;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCod() {
		return errorCod;
	}

	public void setErrorCod(Integer errorCod) {
		this.errorCod = errorCod;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}
}
