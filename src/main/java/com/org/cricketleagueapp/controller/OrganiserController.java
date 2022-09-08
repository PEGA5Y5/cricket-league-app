package com.org.cricketleagueapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cricketleagueapp.entity.Organiser;
import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.service.IOrganiserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/organiser")
public class OrganiserController {
	
	@Autowired
	private IOrganiserService orgnaniserService;
	
	@PostMapping("/add")
	public ResponseEntity<Organiser> insertOrganiser(@RequestBody Organiser organiser) {
		return new ResponseEntity<Organiser>(orgnaniserService.insertOrganiser(organiser),HttpStatus.CREATED);
	}
	
	@GetMapping("/get-organiser-by-id/{organiser-id}")
	public ResponseEntity<Organiser> getOrganiserById(@PathVariable("organiser-id") int organiserId) {
		return new ResponseEntity<Organiser>(orgnaniserService.getOrganiser(organiserId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-all-organisers")
	public  ResponseEntity<List<Organiser>> getAllOrganisers(){
		return new ResponseEntity<List<Organiser>>(orgnaniserService.getAllOrganisers(),HttpStatus.FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Organiser> updateOrganiser(@RequestBody Organiser organiser){
		return new ResponseEntity<Organiser>(orgnaniserService.updateOrganiser(organiser),HttpStatus.OK);
	}
	
	@GetMapping("/get-tournaments/{organiser-id}")
	public  ResponseEntity<List<Tournament>> getTournaments(@PathVariable("organiser-id") int organiserId){
		return new ResponseEntity<List<Tournament>>(orgnaniserService.getTournaments(organiserId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-tournament-by-id/{tournament-id}")
	public ResponseEntity<Tournament> getTournament(@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<Tournament>(orgnaniserService.getTournament(tournamentId),HttpStatus.FOUND);
	}
	
	@PutMapping("/pay-prize-money/{tournament-id}")
	public ResponseEntity<Double> payPrizeMoney(@RequestBody Owner owner, @PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<Double>(orgnaniserService.payPrizeMoney(owner, tournamentId),HttpStatus.OK);
	}
	
	@GetMapping("/get-budget/{organiser-id}")
	public ResponseEntity<Double> getBudget(@PathVariable("organiser-id") int organiserId) {
		return new ResponseEntity<Double>(orgnaniserService.getBudget(organiserId),HttpStatus.FOUND);
	}
}

