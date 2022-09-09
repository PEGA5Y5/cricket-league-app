package com.org.cricketleagueapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;
import com.org.cricketleagueapp.service.ITicketService;

@RequestMapping("/ticket")
@RestController
public class TicketController {
	
	@Autowired
	private ITicketService ticketService;

	public TicketController(ITicketService ticketService) {
		
		this.ticketService = ticketService;
	}
	
	
	@GetMapping("/getTicket/{ticketId}")
	public ResponseEntity<Ticket> getTicket(@PathVariable("ticketId") int ticketId){
		return new ResponseEntity<Ticket>(ticketService.getTicket(ticketId),HttpStatus.OK);
		
	}
	
	@PostMapping
	@RequestMapping("/insertTicket")
	public ResponseEntity<Ticket> insertTicket(@RequestBody Ticket ticket){
		return new ResponseEntity<Ticket>(ticketService.insertTicket(ticket),HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/cancelTicket/{ticketId}")
	public ResponseEntity<String> cancelTicket(@PathVariable("ticketId") int ticketId){
		ticketService.cancelTicket(ticketId);
		return new ResponseEntity<String>("Ticket has canceled successfully",HttpStatus.OK);
	}
	
	@GetMapping("/getMatch")
	public ResponseEntity<Match> getMatch(){
		return new ResponseEntity<Match>(ticketService.getMatch(),HttpStatus.OK);
		
	}
	
	@GetMapping("/getSchedule/{ticketId}")
	public ResponseEntity<Audience> getSchedule(@PathVariable("ticketId") int ticketId){
		return new ResponseEntity<Audience>(ticketService.getSchedule(ticketId),HttpStatus.OK);
		
	}
	
	
	

}
