package com.org.cricketleagueapp.entity;

public class MatchSerialized {

	private long match_id;
	private String matchDate;
	private String startTime;
	private String endTime;
	
	public MatchSerialized(long match_id,String matchDate,String startTime, String endTime) {
		super();
		this.matchDate = matchDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public long getMatch_id() {
		return match_id;
	}

	public void setMatch_id(long match_id) {
		this.match_id = match_id;
	}

	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
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
	public MatchSerialized() {
		super();
	}
	
	
}
