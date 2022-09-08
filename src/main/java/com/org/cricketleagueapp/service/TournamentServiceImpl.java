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
		return tournamentRepository.findById(tournamentId).orElseThrow(() -> 					//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));		//present in the database or not
	}																							//if found tournament object is returned

	@Override
	public List<Tournament> getAllTournaments() {												//returning all the tournament object 
		return tournamentRepository.findAll();													//present in the database
	}

	@Override
	public Tournament insertTournament(Tournament tournament) {
		int tournamentId = tournament.getTournamentId();
		List<Tournament> tournamentList = tournamentRepository.findAll();
		for(Tournament list: tournamentList) {													
			if(list.getTournamentId() == tournamentId) {										//checking if tournament record already exists or not
				throw new DuplicateDataException("Organiser record already exists!");			//if present throwing exception
			}
		}
		return tournamentRepository.save(tournament);											//if not present inserting the object as a new record
	}

	@Override
	public Tournament updateTournament(Tournament tournament) {
		int tournamentId = tournament.getTournamentId();
		Tournament tournamentTemp = tournamentRepository.findById(tournamentId).orElseThrow(() -> 						//through tournament object fetching the record by tournamentId
		new TournamentNotFoundException("Tournament not found with name: " + tournament.getTournamentName()));			//from the database
		tournamentTemp.setMatches(tournament.getMatches());																//updating the same object with new data
		tournamentTemp.setOrganiser(tournament.getOrganiser());
		tournamentTemp.setPrizeMoney(tournament.getPrizeMoney());
		tournamentTemp.setTournamentName(tournament.getTournamentName());
		return tournamentRepository.save(tournamentTemp);																//again saving the object in database
	}

	@Override
	public Tournament deleteTournament(int tournamentId) {
		Tournament temp = tournamentRepository.findById(tournamentId).orElseThrow(() -> 			 //checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			 //present in the database or not
		tournamentRepository.deleteById(tournamentId);												 //after fetching the record from the database deleting the record
		return temp;
	}

	@Override
	public List<Match> getAllMatches(int tournamentId) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 		//through tournamentId fetching the list of matches of that 	
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//particular tournament and returning the list
		return tournament.getMatches();
	}

	@Override
	public Match getMatch(int tournamentId, long matchId) {
		List<Match> match = matchRepository.findAll();																	
		for(Match matchTemp: match) {
			if(matchTemp.getTournament().getTournamentId() == tournamentId && matchTemp.getMatchId() == matchId) {		//fetching a match record matching the tournamentId and matchId
				return matchTemp;																					
			}
		}
		throw new MatchNotFoundException("Match not found with tournamentId: " + tournamentId + "and mathcId: " + matchId);	 //if no match record found throwing exception
	}

	@Override
	public Tournament getTournament(Match match) {						
		long matchId = match.getMatchId();
		Match matchTemp = matchRepository.findById(matchId).orElseThrow(() ->					
		new MatchNotFoundException("Match not found with id"));
		return matchTemp.getTournament();															//fetching a tournament record using the matchId of a match object
	}																								//and returning the tournament record if found

}
