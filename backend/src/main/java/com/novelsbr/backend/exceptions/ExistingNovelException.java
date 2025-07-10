package com.novelsbr.backend.exceptions;

public class ExistingNovelException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ExistingNovelException() {
	}
	
	public ExistingNovelException(String msg) {
		super(msg);
	}
}
