package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Skill;
import com.org.cricketleagueapp.entity.Team;


public interface IPlayerService {

	public Player getPlayer(int playerId);

	public List<Player> getAllPlayers();

	public Player insertPlayer(Player player);

	public Player updatePlayer(Player player);

	public Player deletePlayer(int playerId);

	public Skill getSkill(int playerId);

	public Team getTeam(int playerId);

	public double getSalary(int playerId);

}
