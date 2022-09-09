package com.org.cricketleagueapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;
import com.org.cricketleagueapp.service.IAudienceService;
import com.org.cricketleagueapp.service.IGroundService;

@RequestMapping("/audience")
@RestController
public class AudienceController {
	
	@Autowired
	private IAudienceService audienceService;
    private IGroundService groundService;
	public AudienceController(IAudienceService audienceService) {
		
		this.audienceService = audienceService;
	}
	
	
	@GetMapping("/getAudience/{audienceId}")
	public ResponseEntity<Audience> getAudience(@PathVariable("audienceId") int audienceId){
		return new ResponseEntity<Audience>(audienceService.getAudience(audienceId),HttpStatus.OK);
		
	}
	
	
	@PostMapping
	@RequestMapping("/insertAudience")
	public ResponseEntity<Audience> insertAudience(@RequestBody Audience audience){
		
		return new ResponseEntity<Audience>(audienceService.insertAudience(audience),HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/getMatch/{audienceId}")
	public ResponseEntity<Match> getMatch(@PathVariable("audienceId") int audienceId){
		
		return new ResponseEntity<Match>(audienceService.getMatch(audienceId),HttpStatus.OK);
		
	}
	
	
	@GetMapping("/getAllMatches")
	public ResponseEntity<List<Match>> getAllMatches(){
		return new ResponseEntity<List<Match>>(audienceService.getAllMatches(),HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllTickets")
	public ResponseEntity<List<Ticket>> getAllTickets(){
		return new ResponseEntity<List<Ticket>>(audienceService.getAllTickets(),HttpStatus.OK);
	}
	
	@GetMapping("/getTicket/{ticketId}")
	public ResponseEntity<Ticket> getTicket(@PathVariable("ticketId") int ticketId){
		return new ResponseEntity<Ticket>(audienceService.getTicket(ticketId),HttpStatus.OK);
		
	}
	
	//TODO
	@GetMapping("/getPaidAmountForAllTickects")
	public ResponseEntity<double> getPaidAmountForAllTickects(){
		return new ResponseEntity<double>(audienceService.getPaidAmountForAllTickects(),HttpStatus.OK);
	}
	
	

}
