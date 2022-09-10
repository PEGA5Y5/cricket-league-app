package com.org.cricketleagueapp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Schedule;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.exception.DuplicateDataException;
import com.org.cricketleagueapp.exception.FranchaiseNotFoundException;
import com.org.cricketleagueapp.exception.GroundNotFoundException;
import com.org.cricketleagueapp.exception.MatchNotFoundException;
import com.org.cricketleagueapp.exception.MinimumTeamException;
import com.org.cricketleagueapp.exception.TeamNotFoundException;
import com.org.cricketleagueapp.exception.TournamentDateTimeNotFoundException;
import com.org.cricketleagueapp.exception.TournamentNotFoundException;
import com.org.cricketleagueapp.exception.TournamentPrizeMoneyNotSetException;
import com.org.cricketleagueapp.exception.TournamentStartedException;
import com.org.cricketleagueapp.repository.GroundRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.OwnerRepository;
import com.org.cricketleagueapp.repository.TeamRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;

@Service
public class TournamentServiceImpl implements ITournamentService{
   
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	MatchRepository matchRepository;
	
	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	GroundRepository groundRepository;
	
	@Autowired
	OwnerRepository ownerRepository;
	
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
		String tournamentName = tournament.getTournamentName();
		List<Tournament> tournamentList = tournamentRepository.findAll();
		for(Tournament list: tournamentList) {													
			if(list.getTournamentName().equalsIgnoreCase(tournamentName)) {										//checking if tournament record already exists or not
				throw new DuplicateDataException("Tournament with same name already exists!");			//if present throwing exception
			}
		}
		return tournamentRepository.save(tournament);											//if not present inserting the object as a new record
	}

	@Override
	public Tournament updateTournament(Tournament tournament) {
		int tournamentId = tournament.getTournamentId();
		Tournament tournamentTemp = tournamentRepository.findById(tournamentId).orElseThrow(() -> 						//through tournament object fetching the record by tournamentId
		new TournamentNotFoundException("Tournament not found with name: " + tournament.getTournamentName()));			//from the database	
		if(tournament.getMatches() != null) {																			//updating the same object with new data
			tournamentTemp.setMatches(tournament.getMatches());
		}
		if(tournament.getOrganiser() != null) {
			tournamentTemp.setOrganiser(tournament.getOrganiser());
		}
		if(tournament.getPrizeMoney() > 0) {
			tournamentTemp.setPrizeMoney(tournament.getPrizeMoney());
		}
		if(tournament.getTournamentName() != null) {
			tournamentTemp.setTournamentName(tournament.getTournamentName());
		}
		if(tournament.getEndTime() != null) {
			tournamentTemp.setEndTime(tournament.getEndTime());
		}
		if(tournament.getGroundId() > 0) {
			tournamentTemp.setGroundId(tournament.getGroundId());
		}
		if(tournament.getStartDate() != null) {
			tournamentTemp.setStartDate(tournament.getStartDate());
		}
		if(tournament.getStartTime() != null) {
			tournamentTemp.setStartDate(tournament.getStartDate());
		}
		if(tournament.getTeams() != null) {
			tournamentTemp.setTeams(tournament.getTeams());
		}
		if(tournament.getTournamentStatus() != null) {
			tournamentTemp.setTournamentStatus(tournament.getTournamentStatus());
		}
		return tournamentRepository.save(tournamentTemp);																//again saving the object in database
	}

	@Override
	public Tournament deleteTournament(int tournamentId) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 			 //checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			 //present in the database or not
		
		Ground ground = groundRepository.findById(tournament.getGroundId()).orElseThrow(() -> 		//checking ground record with groundId
		new GroundNotFoundException("Ground not found with id: " + tournament.getGroundId()));		//present or not
		
		ground.setGroundStatus(true);																//releasing the ground
		
		groundRepository.save(ground);
		
		tournamentRepository.deleteById(tournamentId);												 //after fetching the record from the database deleting the record
		return tournament;
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

	@Override
	public Team addTeamToTournament(int tournamentId, int teamId) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 		//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//present in the database or not
		String temp = tournament.getTournamentStatus();
		if(temp.equalsIgnoreCase("started") || temp.equalsIgnoreCase("ended")) {
			throw new TournamentStartedException("Cannot add team! Tournament already started or ended");
		}
		Team team = teamRepository.findById(teamId).orElseThrow(() -> 								//checking team record with teamtId
		new TeamNotFoundException("Team not found with id: " + teamId));							//present in the database or not
		tournament.getTeams().add(team);
		tournamentRepository.save(tournament);
		return team;
	}

	@Override
	public List<Team> getTeams(int tournamentId) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 		//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//present in the database or not
		return tournament.getTeams();
	}

	@Override
	public Team deleteTeamFromTournament(int tournamentId, int teamId) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 		//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//present in the database or not
		List<Team> teams = tournament.getTeams();
		for(Team temp: teams) {
			if(temp.getTeamId() == teamId) {
				teams.remove(temp);
				tournamentRepository.save(tournament);
				return temp;
			}
		}
		throw new TeamNotFoundException("Team not found with id: " + teamId);
	}

	@Override
	public Tournament startTournament(int tournamentId) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 		//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//present in the database or not
		
		if(tournament.getGroundId() == 0) {															//checking all the required field are filled or not
			throw new GroundNotFoundException("No ground selected!");
		}
		if(tournament.getStartDate() == null) {
			throw new TournamentDateTimeNotFoundException("Tournament start date not set!");
		}
		if(tournament.getStartTime() == null) {
			throw new TournamentDateTimeNotFoundException("Match start time not set!");
		}
		if(tournament.getEndTime() == null) {
			throw new TournamentDateTimeNotFoundException("Match end time not set!");
		}
		if(tournament.getTeams().size() < 2) {
			throw new MinimumTeamException("Minimum two teams required!");
		}
		if(tournament.getPrizeMoney() == 0) {
			throw new TournamentPrizeMoneyNotSetException("Tournament prize money not set!");
		}
		tournament.setTournamentStatus("stated");
		
		Ground ground = groundRepository.findById(tournament.getGroundId()).orElseThrow(() -> 		//checking ground record with groundId
		new GroundNotFoundException("Ground not found with id: " + tournament.getGroundId()));		//present or not
		
		List<Match> match = tournament.getMatches();
		
		List<Team> teams = tournament.getTeams();
		
		int noOfTeams = teams.size();
		
		for(int i = 0; i < noOfTeams; i++) {												//generating the matches
			for(int j = 0; i < noOfTeams; i++) {
				if(i != j) {
					Team team1 = teams.get(i);
					Team team2 = teams.get(j);
					match.add(new Match(team1, team2, tournament, ground));
				}
			}
		}
		
		int noOfMatches = match.size();
		
		LocalDate date = tournament.getStartDate();							
		
		tournament.setEndDate(date.plusDays((noOfMatches * 2) - 2));					
		
		LocalTime startTime = tournament.getStartTime();
		LocalTime endTime = tournament.getEndTime();
		
		int count = 0;
		
		for(Match matchTemp: match) {															//assigning schedule to the matches
			matchTemp.setSchedule(new Schedule(date.plusDays(count), startTime, endTime));
			count += 2;																			//count is for giving a day  break between matches
			matchRepository.save(matchTemp);
		}
		
		return tournamentRepository.save(tournament);
	}
	
	@Override
	public Tournament endTournament(int tournamentId, int ownerId) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 		//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//present in the database or not
		
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> 							//checking owner record with ownerId
		new FranchaiseNotFoundException("Owner not found with id: " + ownerId));					//present in the database or not
		
		Ground ground = groundRepository.findById(tournament.getGroundId()).orElseThrow(() -> 		//checking ground record with groundId
		new GroundNotFoundException("Ground not found with id: " + tournament.getGroundId()));		//present or not
		
		ground.setGroundStatus(true);																//releasing the ground
		
		tournament.setTournamentStatus("ended");
		
		owner.setBudget(owner.getBudget() + tournament.getPrizeMoney());
		
		groundRepository.save(ground);
		
		ownerRepository.save(owner);
		
		return tournamentRepository.save(tournament);
	}

}
