package com.org.cricketleagueapp.exception;

public class TournamentNotStartedException extends RuntimeException{
	public TournamentNotStartedException(String msg) {
		super(msg);
	}
}
