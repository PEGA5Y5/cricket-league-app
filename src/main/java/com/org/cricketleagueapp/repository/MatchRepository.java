package com.org.cricketleagueapp.repository;

import java.util.List;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Schedule;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;



public interface MatchRepository extends JpaRepository<Match, Long> {

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
