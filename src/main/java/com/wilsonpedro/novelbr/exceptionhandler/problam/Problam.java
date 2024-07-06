package com.wilsonpedro.novelbr.exceptionhandler.problam;

import java.time.OffsetDateTime;

public class Problam {

	private String title;
	
	private Integer statusCode;
	
	private OffsetDateTime time;
	
	public Problam() {
	}

	public Problam(String title, Integer statusCode, OffsetDateTime time) {
		this.title = title;
		this.statusCode = statusCode;
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public OffsetDateTime getTime() {
		return time;
	}

	public void setTime(OffsetDateTime time) {
		this.time = time;
	}
}
