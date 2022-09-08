package com.org.cricketleagueapp.entity;

public class MatchDummy {

	private int team1_id;
	private int team2_id;
	private int tournament_id;
	private int ground_id;
	private int schedule_id;
	public int getTeam1_id() {
		return team1_id;
	}
	public void setTeam1_id(int team1_id) {
		this.team1_id = team1_id;
	}
	public int getTeam2_id() {
		return team2_id;
	}
	public void setTeam2_id(int team2_id) {
		this.team2_id = team2_id;
	}
	public int getTournament_id() {
		return tournament_id;
	}
	public void setTournament_id(int tournament_id) {
		this.tournament_id = tournament_id;
	}
	public int getGround_id() {
		return ground_id;
	}
	public void setGround_id(int ground_id) {
		this.ground_id = ground_id;
	}
	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public MatchDummy(int team1_id, int team2_id, int tournament_id, int ground_id, int schedule_id) {
		super();
		this.team1_id = team1_id;
		this.team2_id = team2_id;
		this.tournament_id = tournament_id;
		this.ground_id = ground_id;
		this.schedule_id = schedule_id;
	}
	public MatchDummy() {
		super();
	}
	
	
}
