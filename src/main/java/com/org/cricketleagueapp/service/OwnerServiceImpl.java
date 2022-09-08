package com.org.cricketleagueapp.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.exception.FranchaiseNotFoundException;
import com.org.cricketleagueapp.repository.OwnerRepository;

@Service
public class OwnerServiceImpl implements IOwnerService{
	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private PlayerServiceImpl playerServiceImpl;

	@Override
	public Owner getOwner(int ownerId) {
		return ownerRepository.findById(ownerId).orElseThrow(() -> new FranchaiseNotFoundException("Owner with id " + ownerId + "not found"));
	}

	@Override
	public List<Owner> getAllOwners() {
		return ownerRepository.findAll();
	}

	@Override
	public Owner insertOwner(Owner owner) {
		return ownerRepository.save(owner);
	}

	@Override
	public Team getTeam(int ownerId) {
		Owner owner = getOwner(ownerId);
		return owner.getTeam();
	}

	@Override
	public double paySalary(Player player, double salary, int ownerId) {
		Owner owner = getOwner(ownerId);
		owner.setBudget(owner.getBudget() - salary);
		updateOwner(owner, ownerId);
		player.setSalary(player.getSalary() + salary);
		playerServiceImpl.updatePlayer(player, player.getPlayerId());
		return salary;
	}

	@Override
	public double getBudget(int ownerId) {
		Owner owner = getOwner(ownerId);
		return owner.getBudget();
	}

	@Override
	public double getTotalSalary(int ownerId) {
		Owner owner = getOwner(ownerId);
		Team team = owner.getTeam();
		Set<Player> players = team.getPlayers();
		return players.stream().mapToDouble(p -> p.getSalary()).sum();
	}

	@Override
	public Owner updateOwner(Owner owner, int ownerId) {
		getOwner(ownerId);
		owner.setOwnerId(ownerId);
		return insertOwner(owner);
	}

		
}
