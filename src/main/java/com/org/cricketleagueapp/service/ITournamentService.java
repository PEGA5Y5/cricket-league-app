package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Tournament;



public interface ITournamentService {

	public Tournament getTournament(int tournamentId);

	public List<Tournament> getAllTournaments();

	public Tournament insertTournament(Tournament tournament);

	public Tournament updateTournament(Tournament tournament);

	public Tournament deleteTournament(int tournamentId);

	public List<Match> getAllMatches();

	public Match getMatch(int tournamentId, int matchId);

	public Tournament getTournament(Match match);
}
