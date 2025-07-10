package com.novelsbr.backend.exceptions;

public class ExistingAuthorException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExistingAuthorException() {
	}
	
	public ExistingAuthorException(String msg) {
		super(msg);
	}
}
