package com.org.cricketleagueapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Team;



public interface TeamRepository extends JpaRepository<Team, Integer> {
	public Team getTeam(int teamId);

	public List<Team> getAllTeams();

	public Team insertTeam(Team team);

	public Team updateTeam(Team team);

	public Team deleteTeam(int teamId);

	public List<Player> getAllPlayers();

	public Player getPlayer(int teamId, int playerId);

	public Team getTeam(Player player);
}
