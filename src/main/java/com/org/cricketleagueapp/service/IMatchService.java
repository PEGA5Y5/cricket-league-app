package com.org.cricketleagueapp.service;

import java.util.List;

import java.util.Map;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Schedule;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;


public interface IMatchService {

	public Match getMatch(int matchId);

	public Match insertMatch(Match match);

	public Match updateMatch(Match match);

	public List<Team> getTeam1();

	public List<Team> getTeam2();

	public Map<Team, Team> getTeams();

	public Schedule getSchedule(int matchId);

	public Tournament getTournament(int matchId);
	
	public List<Audience> getAllAudience();
	
	public Audience getAudience(int audienceId);

}
