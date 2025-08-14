package com.novelsbr.backend.exceptions;

public class NullFieldException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NullFieldException(String msg) {
		super(msg);
	}
}
