package com.novelsbr.backend.exceptions;

public class TypeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TypeNotFoundException() {
	}
	
	public TypeNotFoundException(String msg) {
		super(msg);
	}
}
