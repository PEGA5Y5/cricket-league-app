package com.org.cricketleagueapp.exception;

public class SeatNotAvailable extends RuntimeException{

	public SeatNotAvailable(String message) {
		super(message);
	}
}
