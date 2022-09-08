package com.org.cricketleagueapp.exception;

public class MatchNotFoundException extends RuntimeException{
	public MatchNotFoundException(String msg) {
		super(msg);
	}
}