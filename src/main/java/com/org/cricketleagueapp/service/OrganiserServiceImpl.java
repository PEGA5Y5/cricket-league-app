package com.org.cricketleagueapp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Organiser;
import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Schedule;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.exception.DuplicateDataException;
import com.org.cricketleagueapp.exception.FranchaiseNotFoundException;
import com.org.cricketleagueapp.exception.GroundNotFoundException;
import com.org.cricketleagueapp.exception.MinimumTeamException;
import com.org.cricketleagueapp.exception.OrganiserNotFoundException;
import com.org.cricketleagueapp.exception.TournamentDateTimeNotFoundException;
import com.org.cricketleagueapp.exception.TournamentNotFoundException;
import com.org.cricketleagueapp.exception.TournamentPrizeMoneyNotSetException;
import com.org.cricketleagueapp.repository.GroundRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.OrganiserRepository;
import com.org.cricketleagueapp.repository.OwnerRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;

@Service
public class OrganiserServiceImpl implements IOrganiserService{
	
	@Autowired
	OrganiserRepository organiserRepository;
	
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	OwnerRepository ownerRepository;
	
	@Autowired
	GroundRepository groundRepository;
	
	@Autowired
	MatchRepository matchRepository;
	

	@Override
	public Organiser getOrganiser(int organiserId) {
		return organiserRepository.findById(organiserId).orElseThrow(() -> 							//checking organiser record with organiserId
		new OrganiserNotFoundException("Organiser not found with id: " + organiserId));			//present in the database or not
	}																								//if found organiser object is returned

	@Override
	public List<Organiser> getAllOrganisers() {														//returning all the organiser object
		return organiserRepository.findAll();														//present in the database
	}

	@Override
	public Organiser insertOrganiser(Organiser organiser) {
		int organiserId = organiser.getOrganiserId();
		List<Organiser> organiserList = organiserRepository.findAll();
		for(Organiser list: organiserList) {													
			if(list.getOrganiserId() == organiserId) {												//checking if organiser record already exists or not
				throw new DuplicateDataException("Organiser record already exists!");				//if present throwing exception
			}
		}
		return organiserRepository.save(organiser);													//if not present inserting the object as a new record
	}

	@Override
	public Organiser updateOrganiser(Organiser organiser) {
		int organiserId = organiser.getOrganiserId();
		Organiser organiserTemp = organiserRepository.findById(organiserId).orElseThrow(() -> 						//through organiser object fetching the record by organiserId
		new OrganiserNotFoundException("Organiser not found with name: " + organiser.getOrganiserName()));			//from the database
		
		if(organiser.getBudget() != 0) {																			//updating the same object with new data			
			organiserTemp.setBudget(organiser.getBudget());	
		}
		if(organiser.getEmail() != null) {
			organiserTemp.setEmail(organiser.getEmail());
		}
		if(organiser.getOrganiserName() != null) {
			organiserTemp.setOrganiserName(organiser.getOrganiserName());
		}
		if(organiser.getPhone() != 0) {
			organiserTemp.setPhone(organiser.getPhone());
		}
		if(organiser.getTournaments() != null) {
			organiserTemp.setTournaments(organiser.getTournaments());
		}
		return organiserRepository.save(organiserTemp);																//again saving the object in database
	}

	@Override
	public List<Tournament> getTournaments(int organiserId) {
		Organiser organiser = organiserRepository.findById(organiserId).orElseThrow(() -> 			//fetching all the tournaments related to the organiserId
		new OrganiserNotFoundException("Organiser not found with id: " + organiserId));
		return organiser.getTournaments();
	}

	@Override
	public Tournament getTournament(int tournamentId) {
		return tournamentRepository.findById(tournamentId).orElseThrow(() -> 						//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//present in the database or not
	}																								//if found tournament object is returned

	@Override
	public double getBudget(int organiserId) {
		Organiser organiser = organiserRepository.findById(organiserId).orElseThrow(() -> 			//returning the budget of the organiser
		new OrganiserNotFoundException("Organiser not found with id: " + organiserId));
		return organiser.getBudget();
	}

	@Override
	public Tournament setTournamentDateTime(int tournamentId, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 		//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//present in the database or not
		
		tournament.setStartDate(startDate);
		tournament.setStartTime(startTime);
		tournament.setEndTime(endTime);
		
		return tournamentRepository.save(tournament);
	}

	@Override
	public Tournament setTournamentGround(int tournamentId, int groundId) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 		//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//present in the database or not
		
		if(groundId == -1) {
			List<Ground> ground = groundRepository.findAll().stream().filter(temp -> temp.isGroundStatus()).collect(Collectors.toCollection(ArrayList::new));		//if groundId is not passed ground will be assigned
			Random rand = new Random();																						//randomly from the list of available grounds
	        int rand_int = rand.nextInt(ground.size()-1);																	//generating random index
	        Ground tournamentGround = ground.get(rand_int);
	        tournament.setGroundId(tournamentGround.getGroundId());
	        tournamentGround.setGroundStatus(false);
	        groundRepository.save(tournamentGround);
	        return tournamentRepository.save(tournament);
		}
		else {
			Ground ground = groundRepository.findById(groundId).orElseThrow(() -> 		//checking ground record with groundId
			new GroundNotFoundException("Ground not found with id: " + groundId));		//present or not
			ground.setGroundStatus(false);
			tournament.setGroundId(ground.getGroundId());
			groundRepository.save(ground);
		    return tournamentRepository.save(tournament);
		}
	}
	
	@Override
	public Tournament setTournamentGround(int tournamentId) {						//for random generating ground
		return setTournamentGround(tournamentId, 0);
	}

	@Override
	public Tournament setTournamentPrizeMoney(int tournamentId, double prizeMoney) {
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 		//checking tournament record with tournamentId
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));			//present in the database or not
		
		tournament.setPrizeMoney(prizeMoney);
		
		return tournamentRepository.save(tournament);
	}
	
	@Override
	public List<Ground> getAllGrounds() {
		return groundRepository.findAll().stream().filter(temp -> temp.isGroundStatus()).collect(Collectors.toCollection(ArrayList::new));
	}
}
