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
		return organiserRepository.findById(organiserId).orElseThrow(() -> 
		new OrganiserNotFoundException("Tournament not found with id: " + organiserId));
	}

	@Override
	public List<Organiser> getAllOrganisers() {
		return organiserRepository.findAll();
	}

	@Override
	public Organiser insertOrganiser(Organiser organiser) {
		int organiserId = organiser.getOrganiserId();
		List<Organiser> organiserList = organiserRepository.findAll();
		for(Organiser list: organiserList) {													//checking if organiser record already exists or not
			if(list.getOrganiserId() == organiserId) {
				throw new DuplicateDataException("Organiser record already exists!");
			}
		}
		return organiserRepository.save(organiser);
	}

	@Override
	public Organiser updateOrganiser(Organiser organiser) {
		int organiserId = organiser.getOrganiserId();
		Organiser organiserTemp = organiserRepository.findById(organiserId).orElseThrow(() -> 
		new OrganiserNotFoundException("Organiser not found with name: " + organiser.getOrganiserName()));
		organiserTemp.setBudget(organiser.getBudget());
		organiserTemp.setEmail(organiser.getEmail());
		organiserTemp.setOrganiserName(organiser.getOrganiserName());
		organiserTemp.setPayment(organiser.getPayment());
		organiserTemp.setPhone(organiser.getPhone());
		organiserTemp.setTournaments(organiser.getTournaments());
		return organiserRepository.save(organiserTemp);
	}

	@Override
	public List<Tournament> getTournaments(int organiserId) {
		Organiser organiser = organiserRepository.findById(organiserId).orElseThrow(() -> 
		new OrganiserNotFoundException("Organiser not found with id: " + organiserId));
		return organiser.getTournaments();
	}

	@Override
	public Tournament getTournament(int tournamentId) {
		return tournamentRepository.findById(tournamentId).orElseThrow(() -> 
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));
	}

	@Override
	public double payPrizeMoney(Owner owner, int tournamentId) {
		int ownerId = owner.getOwnerId();
		Owner ownerTemp = ownerRepository.findById(ownerId).orElseThrow(() -> 
		new FranchaiseNotFoundException("Owner not found with id: " + ownerId));
		Tournament tournament = tournamentRepository.findById(tournamentId).orElseThrow(() -> 
		new TournamentNotFoundException("Tournament not found with id: " + tournamentId));
		Organiser organiser = tournament.getOrganiser();
		double prizeMoney = tournament.getPrizeMoney();
		organiser.setPayment(organiser.getPayment() - prizeMoney);
		organiserRepository.save(organiser);
		ownerTemp.setBudget(ownerTemp.getBudget() + prizeMoney);
		ownerRepository.save(ownerTemp);
		return prizeMoney;
	}

	@Override
	public double getBudget(int organiserId) {
		Organiser organiser = organiserRepository.findById(organiserId).orElseThrow(() -> 
		new OrganiserNotFoundException("Organiser not found with id: " + organiserId));
		return organiser.getBudget();
	}
}
