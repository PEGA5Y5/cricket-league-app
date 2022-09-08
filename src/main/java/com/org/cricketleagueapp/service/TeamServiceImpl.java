package com.org.cricketleagueapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.exception.TeamNotFoundException;
import com.org.cricketleagueapp.repository.TeamRepository;

@Service
public class TeamServiceImpl implements ITeamService{
	
	@Autowired
	private TeamRepository teamRepository;

	@Override
	public Team getTeam(int teamId) {
		return teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException("Team with id " + teamId + "not found"));
	}

	@Override
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@Override
	public Team insertTeam(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public Team updateTeam(Team team, int teamId) {
		getTeam(teamId);
		team.setTeamId(teamId);
		return insertTeam(team);
	}

	@Override
	public Team deleteTeam(int teamId) {
		Team team = getTeam(teamId);
		teamRepository.deleteById(teamId);
		return team;
	}

	@Override
	public List<Player> getAllPlayers(int teamId) {
		return getTeam(teamId).getPlayers();
	}

	@Override
	public Player getPlayer(int teamId, int playerId) {
		List<Player> players = getAllPlayers(teamId);
		return players.stream().filter(p -> p.getPlayerId() == playerId).findAny().orElseThrow(() -> new TeamNotFoundException("Team with id " + teamId + "not found"));
	}

	// Repeated in Player Service
	/*
	 * @Override public Team getTeam(Player player) { // TODO Auto-generated method
	 * stub return null; }
	 */
	

}
