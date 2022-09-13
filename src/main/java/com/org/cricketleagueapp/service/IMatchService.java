package com.org.cricketleagueapp.service;


import java.util.Map;
import java.util.Set;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Schedule;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;

public interface IMatchService {
	public Match insertMatch(Match match);
	public Match getMatch(long matchId);
	public Match updateMatch(Match match);
	public Match deleteMatchById(long id);
	public Team getTeam1(long id);
	public Team getTeam2(long id);
	public Map<String, Team> getTeams(long id);
	public Tournament getTournament(long matchId);
	public Schedule getSchedule(long matchId);
	public Set<Audience> getAllAudience(long matchId);
	public Audience getAudience(int audienceId);
}
