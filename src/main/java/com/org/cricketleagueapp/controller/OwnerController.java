package com.org.cricketleagueapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.exception.DuplicateDataException;
import com.org.cricketleagueapp.exception.InsufficientBalanceException;
import com.org.cricketleagueapp.exception.PlayerNotFoundException;
import com.org.cricketleagueapp.exception.TeamNotFoundException;
import com.org.cricketleagueapp.service.IOwnerService;
import com.org.cricketleagueapp.service.IPlayerService;
import com.org.cricketleagueapp.service.ITeamService;
import com.org.cricketleagueapp.service.ITournamentService;

@RestController
@RequestMapping("/own")
public class OwnerController {

	@Autowired
	private IOwnerService ownerService;
	
	@Autowired
	private ITeamService teamService;
	
	@Autowired
	private IPlayerService playerService;
	
	@Autowired
	private ITournamentService tournamentService;
	
	
	@PostMapping
	@RequestMapping("/save")
	public ResponseEntity<Owner> saveOwner(@RequestBody Owner owner) {
		return new ResponseEntity<Owner>(ownerService.insertOwner(owner), HttpStatus.CREATED);
	}
	
	@PostMapping
	@RequestMapping("/addteam/{ownerId}")
	public ResponseEntity<Team> saveTeam(@RequestBody Team team, @PathVariable("ownerId") int ownerId) {
		Owner owner = ownerService.getOwner(ownerId);
		teamService.insertTeam(team);
		owner.setTeam(teamService.getTeam(team.getTeamId()));
		ownerService.updateOwner(owner);
		return new ResponseEntity<Team>(owner.getTeam(), HttpStatus.CREATED);
	}
	
	@GetMapping
	@RequestMapping("/allteam")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}
	
	@GetMapping
	@RequestMapping("/team/{id}")
	public Team getTeam(@PathVariable("id") int ownerId) {
		return ownerService.getOwner(ownerId).getTeam();
	}
	
	@GetMapping
	@RequestMapping("/allplayers/{id}")
	public List<Player> getAllTeamPlayers(@PathVariable("id") int ownerId) {
		return ownerService.getOwner(ownerId).getTeam().getPlayers();
	}
	
	@DeleteMapping
	@RequestMapping("/deleteteam/{ownerId}")
	public Team deleteTeam(@PathVariable("ownerId") int ownerId) {
		Owner owner = ownerService.getOwner(ownerId);
		int teamId = owner.getTeam().getTeamId();
		owner.setTeam(null);
		ownerService.updateOwner(owner);
		return teamService.deleteTeam(teamId);
	}
	
	@DeleteMapping
	@RequestMapping("/delete/{id}")
	public ResponseEntity<Owner> deleteOwner(@PathVariable("id") int ownerId) {
		return new ResponseEntity<Owner>(ownerService.deleteOwner(ownerId), HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping("/get/{id}")
	public ResponseEntity<Owner> getOwner(@PathVariable("id") int ownerId) {
		return new ResponseEntity<Owner>(ownerService.getOwner(ownerId), HttpStatus.FOUND);
	}
	
	@GetMapping
	@RequestMapping("/all")
	public List<Owner> getAllOwners() {
		return ownerService.getAllOwners();
	}
	
	@PutMapping
	@RequestMapping("/updateteam")
	public Team updateTeam(@RequestBody Team team) {
		return teamService.updateTeam(team);
	}
	
	@PutMapping
	@RequestMapping("/addplayer/{ownerId}")
	public Player addPlayerTeam(@RequestBody Player player, @PathVariable("ownerId") int ownerId) {
		Team team = ownerService.getOwner(ownerId).getTeam();
		playerService.getPlayer(player.getPlayerId());
		player.setTeam(team);
		playerService.updatePlayer(player);
		return player;
	}
	
	@PutMapping
	@RequestMapping("/removeplayer/{ownerId}")
	public List<Player> removePlayerTeam(@RequestBody Player player, @PathVariable("ownerId") int ownerId) {
		Team team = ownerService.getOwner(ownerId).getTeam();
		Player player2 = playerService.getPlayer(player.getPlayerId());
		if(player2.getTeam() == null) 
			throw new PlayerNotFoundException("player does not have any team");
		if(player2.getTeam().equals(team))
		{
			player2.setTeam(null);
			playerService.updatePlayer(player2);
			return teamService.getAllPlayers(team.getTeamId());
		}
		else {
			throw new PlayerNotFoundException("player not found");
		}
		
		
	}
	
	
//	@PutMapping
//	@RequestMapping("/buyteam/{id}")
//	public Team buyTeam(@RequestBody Owner owner, @PathVariable("id") int teamId) {
//		//int ownerId = owner.getOwnerId();
//		if(owner.getTeam() != null)
//			throw new DuplicateDataException("Owner already own a team");
//		owner.setTeam(teamService.getTeam(teamId));
//		ownerService.updateOwner(owner);
//		teamService.updateTeam(teamService.getTeam(teamId));
//		return teamService.getTeam(teamId);
//	}
	
	@PutMapping
	@RequestMapping("/buyteam/{ownerId}/{teamId}")
	public Team buyTeam(@PathVariable("ownerId") int ownerId, @PathVariable("teamId") int teamId) {
		Owner owner = ownerService.getOwner(ownerId);
		Team team = teamService.getTeam(teamId);
		if(owner.getTeam() == null) {
			owner.setTeam(team);
			ownerService.updateOwner(owner);
			return team;
		}
		else {
			throw new TeamNotFoundException("Owner already owns a team");
		}
		
	}
	
	@PutMapping
	@RequestMapping("/releaseteam/{ownerId}/{teamId}")
	public Team releaseTeam(@PathVariable("ownerId") int ownerId, @PathVariable("teamId") int teamId) {
		Owner owner = ownerService.getOwner(ownerId);
		Team team = teamService.getTeam(teamId);
		if(owner.getTeam() == null)
			throw new TeamNotFoundException("Owner does not have any team");
		if(owner.getTeam().equals(team))
		{
			owner.setTeam(null);
			ownerService.updateOwner(owner);
			return team;
		}
		else {
			throw new TeamNotFoundException("Owner does not own this team");
		}
		
	}
	
	@PutMapping("/enrollTeam/{tournament-id}/{team-id}")
	public  ResponseEntity<Team> addTeamToTournament(@PathVariable("tournament-id") int tournamentId, @PathVariable("team-id") int teamId) {
		return new ResponseEntity<Team>(tournamentService.addTeamToTournament(tournamentId, teamId),HttpStatus.OK);
	}
	
	@PutMapping("/payplayer/{playerId}/{ownerId}/{payment}")
	public ResponseEntity<Player> paySalary(@PathVariable("playerId") int playerId,@PathVariable("ownerId") int ownerId,@PathVariable("payment") int payment){
		Owner owner = ownerService.getOwner(ownerId);
		Team team = owner.getTeam();
		Player player = playerService.getPlayer(playerId);
		if(!(player.getTeam().equals(team)))
			throw new TeamNotFoundException("Player is not in team");
		double playerSal = player.getSalary();
		if(owner.getBudget()<payment) {
			throw new InsufficientBalanceException("Insufficient Balance");
		}
		player.setSalary(playerSal + payment);
		owner.setBudget(owner.getBudget() - payment);
		playerService.updatePlayer(player);
		ownerService.updateOwner(owner);
		return new ResponseEntity<Player>(playerService.getPlayer(playerId),HttpStatus.OK);
	}
	
}
