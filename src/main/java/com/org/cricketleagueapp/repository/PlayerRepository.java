package com.org.cricketleagueapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Skill;
import com.org.cricketleagueapp.entity.Team;



public interface PlayerRepository extends JpaRepository<Player, Integer> {
	public Player getPlayer(int playerId);

	public List<Player> getAllPlayers();

	public Player insertPlayer(Player player);

	public Player updatePlayer(Player player);

	public Player deletePlayer(int playerId);

	public Skill getSkill();

	public Team getTeam();

	public double getSalary();

	public double getSalary(int tournamentId);
}
