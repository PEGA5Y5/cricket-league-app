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

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Ticket;
import com.org.cricketleagueapp.service.IAudienceService;
import com.org.cricketleagueapp.service.IMatchService;
import com.org.cricketleagueapp.service.ITeamService;
import com.org.cricketleagueapp.service.ITicketService;

@RestController
@RequestMapping("/audience")
public class AudienceController {
	@Autowired
	private IAudienceService audienceService;
	
	@Autowired
	private IMatchService matchService;
	
	@Autowired
	private ITicketService ticketService;

	@GetMapping("{audienceId}")
	public ResponseEntity<Audience> getAudience(@PathVariable int audienceId){
		return new ResponseEntity<Audience>(audienceService.getAudience(audienceId),HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<Audience> insertAudience(@RequestBody Audience audience){
		/*Match match = matchService.getMatch(matchId);
		audience.setMatches(match);*/
		return new ResponseEntity<Audience>(audienceService.insertAudience(audience),HttpStatus.CREATED);
	}
	
	@GetMapping("/getmatch/{audienceId}")
	public ResponseEntity<Match> getMatch(@PathVariable int audienceId){
		return new ResponseEntity<Match>(audienceService.getMatch(audienceId),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Audience> updateAudience(@RequestBody Audience audience){
		return new ResponseEntity<Audience>(audienceService.updateAudience(audience), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{audienceId}")
	public ResponseEntity<Audience> deleteAudience(@PathVariable int audienceId){
		return new ResponseEntity<Audience>(audienceService.deleteAudience(audienceId),HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public List<Audience> getAllAudience(){
		return audienceService.getAllAudience();
	}
	
	//Ticket Controller
	@PostMapping("/buyticket/{audienceId}/{matchId}")
	public ResponseEntity<Ticket> buyTicket(@RequestBody Ticket ticket, @PathVariable int audienceId, @PathVariable int matchId){
		Audience audience = audienceService.getAudience(audienceId);
		Match match = matchService.getMatch(matchId);
		ticket.setTicketName(audience.getAudienceName() +" "+ticket.getNoOfSeats()+" "+match.getGround().getGroundName());
		ticket.setMatch(match);
		return new ResponseEntity<Ticket>(ticketService.insertTicket(ticket, audienceId),HttpStatus.CREATED);
	}
	
	@GetMapping("/getticket/{ticketId}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable int ticketId){
		return new ResponseEntity<Ticket>(ticketService.getTicket(ticketId),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteticket/{ticketId}")
	public ResponseEntity<Ticket> deleteTicket(@PathVariable int ticketId){
		return new ResponseEntity<Ticket>(ticketService.cancelTicket(ticketId),HttpStatus.OK);
	}
	
	@PutMapping("/updateticket")
	public ResponseEntity<Ticket> updateAudience(@RequestBody Ticket ticket){
		return new ResponseEntity<Ticket>(ticketService.updateTicket(ticket), HttpStatus.OK);
	}
}
