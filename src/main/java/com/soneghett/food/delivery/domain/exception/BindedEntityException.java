package com.soneghett.food.delivery.domain.exception;

public class BindedEntityException extends RuntimeException {

	private static final long serialVersionUID = 4091679344866054772L;

	public BindedEntityException(String message) {
		super(message);
	}
}
