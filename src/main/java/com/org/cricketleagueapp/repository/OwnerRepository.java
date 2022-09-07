package com.org.cricketleagueapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Team;



public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	public Owner getOwner(int ownerId);

	public List<Owner> getAllOwners();

	public Owner insertOwner(Owner owner);

	public Owner updateOwner(Owner owner);

	public Team getTeam();

	public double paySalary(Player player, double salary);

	public double getBudget();

	public double getTotalSalary(List<Player> player);
}
