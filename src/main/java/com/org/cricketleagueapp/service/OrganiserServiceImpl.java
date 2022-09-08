package com.org.cricketleagueapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.org.cricketleagueapp.entity.Organiser;
import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.exception.DuplicateDataException;
import com.org.cricketleagueapp.exception.FranchaiseNotFoundException;
import com.org.cricketleagueapp.exception.OrganiserNotFoundException;
import com.org.cricketleagueapp.exception.TournamentNotFoundException;
import com.org.cricketleagueapp.repository.OrganiserRepository;
import com.org.cricketleagueapp.repository.OwnerRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;

public class OrganiserServiceImpl implements IOrganiserService{
	
	@Autowired
	OrganiserRepository organiserRepository;
	
	@Autowired
	TournamentRepository tournamentRepository;
	
	@Autowired
	OwnerRepository ownerRepository;

	@Override
	public Organiser getOrganiser(int organiserId) {
		return organiserRepository.findById(organiserId).orElseThrow(() -> 							//checking organiser record with organiserId
		new OrganiserNotFoundException("Tournament not found with id: " + organiserId));			//present in the database or not
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
		organiserTemp.setBudget(organiser.getBudget());																//updating the same object with new data			
		organiserTemp.setEmail(organiser.getEmail());
		organiserTemp.setOrganiserName(organiser.getOrganiserName());
		organiserTemp.setPayment(organiser.getPayment());
		organiserTemp.setPhone(organiser.getPhone());
		organiserTemp.setTournaments(organiser.getTournaments());
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
	public double payPrizeMoney(Owner owner, int tournamentId) {
		int ownerId = owner.getOwnerId();
		Owner ownerTemp = ownerRepository.findById(ownerId).orElseThrow(() -> 						//fetching the owner and tournament record from the database
		new FranchaiseNotFoundException("Owner not found with id: " + ownerId));
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));
		Organiser organiser = tournament.getOrganiser();		
		double prizeMoney = tournament.getPrizeMoney();												//fetching the tournament prize money from the tournament record
		organiser.setPayment(organiser.getPayment() - prizeMoney);									//deducting the prize money from the organiser's payment field 
		organiserRepository.save(organiser);
		ownerTemp.setBudget(ownerTemp.getBudget() + prizeMoney);									//adding the prize money to the owner's budget
		ownerRepository.save(ownerTemp);
		return prizeMoney;
	}

	@Override
	public double getBudget(int organiserId) {
		Organiser organiser = organiserRepository.findById(organiserId).orElseThrow(() -> 			//returning the budget of the organiser
		new OrganiserNotFoundException("Organiser not found with id: " + organiserId));
		return organiser.getBudget();
	}
}
