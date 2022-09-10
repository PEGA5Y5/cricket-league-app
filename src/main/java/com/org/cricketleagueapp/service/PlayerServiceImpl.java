package com.org.cricketleagueapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Skill;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.exception.PlayerNotFoundException;
import com.org.cricketleagueapp.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements IPlayerService {
	
	@Autowired
	public PlayerRepository playerRepository;
	

	@Override
	public Player getPlayer(int playerId) {
		return playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player with id " + playerId + "not found"));
	}

	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@Override
	public Player insertPlayer(Player player) {
		return playerRepository.save(player);
	}

	@Override
	public Player updatePlayer(Player player) {
		int playerId = player.getPlayerId();
		Player oldPlayer = getPlayer(playerId);
		Player savePlayer = new Player();
		savePlayer.setPlayerId(playerId);
		if(player.getPlayerName() != null) 
		{
			savePlayer.setPlayerName(player.getPlayerName());
		}
		else {
			savePlayer.setPlayerName(oldPlayer.getPlayerName());
		}	
		if(player.getSalary() != 0) 
			savePlayer.setSalary(player.getSalary());
		else {
			savePlayer.setSalary(oldPlayer.getSalary());
		}
		if(player.getSkill() != null)
			savePlayer.setSkill(player.getSkill());
		else {
			savePlayer.setSkill(oldPlayer.getSkill());
		}
		if(player.getTeam() != null)
			savePlayer.setTeam(player.getTeam());
		else {
			savePlayer.setTeam(oldPlayer.getTeam());
		}
		playerRepository.save(savePlayer);
		return getPlayer(playerId);
		
	}

	@Override
	public Player deletePlayer(int playerId) {
		Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player with id " + playerId + "not found"));
		playerRepository.deleteById(playerId);
		return player;
	}

	@Override
	public Skill getSkill(int playerId) {
		Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player with id " + playerId + "not found"));
		return player.getSkill();
	}

	@Override
	public Team getTeam(int playerId) {
		Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player with id " + playerId + "not found"));
		return player.getTeam();
	}

	@Override
	public double getSalary(int playerId) {
		Player player = playerRepository.findById(playerId).orElseThrow(() -> new PlayerNotFoundException("Player with id " + playerId + "not found"));
		return player.getSalary();
	}

//	@Override
//	public double getSalary(int tournamentId, int playerId) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	

}
