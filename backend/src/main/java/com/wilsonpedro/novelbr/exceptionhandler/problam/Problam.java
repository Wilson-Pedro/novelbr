package com.wilsonpedro.novelbr.exceptionhandler.problam;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Problam implements Serializable{
	private static final long serialVersionUID = 1L;

	private String title;
	
	private Integer statusCode;
	
	private OffsetDateTime time;
	
	private List<Field> fields = new ArrayList<>();
	
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

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
