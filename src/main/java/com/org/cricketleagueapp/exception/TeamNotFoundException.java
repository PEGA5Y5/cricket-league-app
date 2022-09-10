package com.org.cricketleagueapp.exception;

public class TeamNotFoundException extends RuntimeException {

	public TeamNotFoundException(String message) {
		super(message);
	}
}
