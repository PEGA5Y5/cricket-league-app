package com.org.cricketleagueapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Tournament;



public interface TournamentRepository extends JpaRepository<Tournament, Integer> {
	public Tournament getTournament(int tournamentId);

	public List<Tournament> getAllTournaments();

	public Tournament insertTournament(Tournament tournament);

	public Tournament updateTournament(Tournament tournament);

	public Tournament deleteTournament(int tournamentId);

	public List<Match> getAllMatches();

	public Match getMatch(int tournamentId, int matchId);

	public Tournament getTournament(Match match);
}
