package com.org.cricketleagueapp.entity;

public class TournamentSerialized {
	
	private String startDate;
	private String startTime;
	private String endTime;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public TournamentSerialized(String startDate, String startTime, String endTime) {
		super();
		this.startDate = startDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public TournamentSerialized() {
		super();
	}
	
}
