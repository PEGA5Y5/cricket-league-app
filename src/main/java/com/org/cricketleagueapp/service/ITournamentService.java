package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;



public interface ITournamentService {

	public Tournament getTournament(int tournamentId);

	public List<Tournament> getAllTournaments();

	public Tournament insertTournament(Tournament tournament);

	public Tournament updateTournament(Tournament tournament);

	public Tournament deleteTournament(int tournamentId);

	public List<Match> getAllMatches(int tournamentId);

	public Match getMatch(int tournamentId, long matchId);

	public Tournament getTournament(Match match);
	
	public Team addTeamToTournament(int tournamentId, int teamId);
	
	public List<Team> getTeams(int tournamentId);
	
	public Team deleteTeamFromTournament(int tournamentId, int teamId);
	
	public Tournament startTournament(int tournamentId);

	public Tournament endTournament(int tournamentId, int ownerId);
}
