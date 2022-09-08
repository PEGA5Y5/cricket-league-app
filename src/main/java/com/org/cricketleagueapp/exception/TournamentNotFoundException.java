package com.org.cricketleagueapp.exception;

public class TournamentNotFoundException extends RuntimeException{
	public TournamentNotFoundException(String msg) {
		super(msg);
	}
}
