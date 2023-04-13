package com.sandes.speedyDrive.services.exceptions;

public class EntityNotUpdadeOrDeleteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityNotUpdadeOrDeleteException(String msg) {
		super(msg);
	}
}
