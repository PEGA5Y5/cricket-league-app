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

import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.service.IGroundService;

@RestController
@RequestMapping("/ground")
public class GroundController {

	@Autowired
	private IGroundService groundService;
	
	@GetMapping("/viewAllMatchesbyGroundId/{groundId}")
	public List<Match> getAllMatchesGround(@PathVariable int groundId){
		return groundService.getAllmatchesGround(groundId);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Ground>insertGround(@RequestBody Ground ground){
	 return new ResponseEntity<Ground>(groundService.insertGround(ground),HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Ground>updateGround(@RequestBody Ground ground){
		 Ground updateGround= groundService.updateGround(ground);
	     return new ResponseEntity<>(updateGround,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteGroundbyId/{groundId}")
	public ResponseEntity<String> deleteGround(@PathVariable int groundId) {
		groundService.deleteGround(groundId);
		return new ResponseEntity<String>("Ground deleted successfully",HttpStatus.OK);
	}
	
	@GetMapping("/viewAllMatches")
	public List<Match> getAllMatches(){
		return groundService.getAllMatches();
	}
	
	@GetMapping("/viewMatch/{matchId}")
	public Match getMatch(@PathVariable long matchId){
		return groundService.getMatch(matchId);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Ground> getGround(@PathVariable int id){
		return new ResponseEntity<Ground>(groundService.getGround(id),HttpStatus.OK);
	}
	
}
