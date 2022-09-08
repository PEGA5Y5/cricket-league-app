package com.org.cricketleagueapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.exception.DuplicateDataException;
import com.org.cricketleagueapp.exception.MatchNotFoundException;
import com.org.cricketleagueapp.exception.TournamentNotFoundException;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;

@Service
public class TournamentServiceImpl implements ITournamentService{
   
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	MatchRepository matchRepository;
	
	@Override
	public Tournament getTournament(int tournamentId) {
		return tournamentRepository.findById(tournamentId).orElseThrow(() -> 
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));
	}

	@Override
	public List<Tournament> getAllTournaments() {
		return tournamentRepository.findAll();
	}

	@Override
	public Tournament insertTournament(Tournament tournament) {
		int tournamentId = tournament.getTournamentId();
		List<Tournament> tournamentList = tournamentRepository.findAll();
		for(Tournament list: tournamentList) {													//checking if tournament record already exists or not
			if(list.getTournamentId() == tournamentId) {
				throw new DuplicateDataException("Organiser record already exists!");
			}
		}
		return tournamentRepository.save(tournament);
	}

	@Override
	public Tournament updateTournament(Tournament tournament) {
		int tournamentId = tournament.getTournamentId();
		Tournament tournamentTemp = tournamentRepository.findById(tournamentId).orElseThrow(() -> 
		new TournamentNotFoundException("Tournament not found with name: " + tournament.getTournamentName()));
		tournamentTemp.setMatches(tournament.getMatches());
		tournamentTemp.setOrganiser(tournament.getOrganiser());
		tournamentTemp.setPrizeMoney(tournament.getPrizeMoney());
		tournamentTemp.setTournamentName(tournament.getTournamentName());
		return tournamentRepository.save(tournamentTemp);
	}

	@Override
	public Tournament deleteTournament(int tournamentId) {
		Tournament temp = tournamentRepository.findById(tournamentId).orElseThrow(() -> 
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));
		tournamentRepository.deleteById(tournamentId);
		return temp;
	}

	@Override
	public List<Match> getAllMatches(int tournamentId) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));
		return tournament.getMatches();
	}

	@Override
	public Match getMatch(int tournamentId, int matchId) {
		List<Match> match = matchRepository.findAll();
		for(Match matchTemp: match) {
			if(matchTemp.getTournament().getTournamentId() == tournamentId && matchTemp.getMatchId() == matchId) {
				return matchTemp;
			}
		}
		throw new MatchNotFoundException("Match not found with tournamentId: " + tournamentId + "and mathcId: " + matchId);
	}

	@Override
	public Tournament getTournament(Match match) {
		long matchId = match.getMatchId();
		Match matchTemp = matchRepository.findById(matchId).orElseThrow(() ->
		new MatchNotFoundException("Match not found with id"));
		return matchTemp.getTournament();
	}

}
