package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Team;



public interface ITeamService {

	public Team getTeam(int teamId);

	public List<Team> getAllTeams();

	public Team insertTeam(Team team);

	public Team updateTeam(Team team);

	public Team deleteTeam(int teamId);

	public List<Player> getAllPlayers();

	public Player getPlayer(int teamId, int playerId);

	public Team getTeam(Player player);

}
