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

import com.org.cricketleagueapp.entity.Player;
import com.org.cricketleagueapp.entity.Skill;
import com.org.cricketleagueapp.service.IPlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	private IPlayerService playerService;
	
	
	@PostMapping
	@RequestMapping("/save")
	public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
		return new ResponseEntity<Player>(playerService.insertPlayer(player), HttpStatus.CREATED);
	}
	
	@GetMapping
	@RequestMapping("/get/{id}")
	public ResponseEntity<Player> getPlayer(@PathVariable("id") int playerId) {
		return new ResponseEntity<Player>(playerService.getPlayer(playerId), HttpStatus.FOUND);
	}
	
	@GetMapping
	@RequestMapping("/all")
	public List<Player> getAllPlayer() {
		return playerService.getAllPlayers();
	}
	
	
	@GetMapping
	@RequestMapping("/skill/{id}")
	public String getSkill(@PathVariable("id") int playerId) {
		Skill skill = playerService.getSkill(playerId);
		return skill.toString();
	}
	
	
	@PutMapping
	@RequestMapping("/update")
	public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
		return new ResponseEntity<Player>(playerService.updatePlayer(player), HttpStatus.CREATED);
	}
	
	@DeleteMapping
	@RequestMapping("/delete/{id}")
	public ResponseEntity<Player> deletePlayer(@PathVariable("id") int playerId) {
		return new ResponseEntity<Player>(playerService.deletePlayer(playerId), HttpStatus.OK);
	}
	
	
	
	
	

}
