package com.crudjava.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 705920653430728112L;

	public BusinessException(String msg) {
		super(msg);
	}
}
