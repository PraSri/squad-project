package com.squad.parkinglot.exception;

public class ParkingException extends Exception {

	/**
	 * Class to handle exception for parking lot application and print message
	 */
	private static final long serialVersionUID = 1L;

	public ParkingException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ParkingException(String message) {
		super(message);
	}

}
