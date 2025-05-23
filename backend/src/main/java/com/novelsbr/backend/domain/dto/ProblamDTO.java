package com.novelsbr.backend.domain.dto;

import java.time.Instant;

public class ProblamDTO {

	private String title;
	private Integer errorCod;
	private Instant instant;
	
	public ProblamDTO() {
	}

	public ProblamDTO(String title, Integer errorCod, Instant instant) {
		this.title = title;
		this.errorCod = errorCod;
		this.instant = instant;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
