package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Team;



public interface IOwnerService {

	public Owner getOwner(int ownerId);

	public List<Owner> getAllOwners();

	public Owner insertOwner(Owner owner);

	public Owner updateOwner(Owner owner);
	
	public Owner deleteOwner(int ownerId);

	public Team getTeam(int ownerId);

	public double paySalary(Player player, double salary, int ownerId);

	public double getBudget(int ownerId);

	public double getTotalSalary(int ownerId);
}
