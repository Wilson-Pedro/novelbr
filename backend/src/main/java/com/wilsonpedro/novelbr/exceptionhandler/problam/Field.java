package com.wilsonpedro.novelbr.exceptionhandler.problam;

import java.io.Serializable;

public class Field implements Serializable{
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String message;
	
	public Field() {
	}
	
	public Field(String name, String message) {
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
