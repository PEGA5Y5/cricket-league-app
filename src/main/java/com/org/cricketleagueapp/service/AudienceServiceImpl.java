package com.org.cricketleagueapp.service;

import java.util.List;


import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;
import com.org.cricketleagueapp.exception.AudienceNotFoundException;
import com.org.cricketleagueapp.exception.MatchNotFoundException;
import com.org.cricketleagueapp.exception.TicketNotFoundException;
import com.org.cricketleagueapp.repository.AudienceRepository;
import com.org.cricketleagueapp.repository.GroundRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.TicketRepository;

public class AudienceServiceImpl implements IAudienceService {
	
	private AudienceRepository audienceRepository;
	private MatchRepository matchRepository;
	private TicketRepository ticketRepository;
    
	

	public AudienceServiceImpl(AudienceRepository audienceRepository) {
		super();
		this.audienceRepository = audienceRepository;
	}

	@Override
	public Audience getAudience(int audienceId) {
		// TODO Auto-generated method stub
		return audienceRepository.findById(audienceId).orElseThrow(() -> new AudienceNotFoundException("Audience not found with id: " + audienceId));
		
	}

	//TODO
	@Override
	public Audience insertAudience(Audience audience) {
		// TODO Auto-generated method stub
		Match match=audience.getMatches();
		Ground ground=match.getGround();
		//if(ground.getCapacity())
		return audienceRepository.save(audience);
	}

	@Override
	public Match getMatch(int audienceId) {
		// TODO Auto-generated method stub
		
		Audience audience=audienceRepository.findById(audienceId).orElseThrow(() -> new AudienceNotFoundException("Audience not found with id: " + audienceId));;
		return audience.getMatches();
	
	}

	@Override
	public List<Match> getAllMatches() {
		// TODO Auto-generated method stub
		return matchRepository.findAll();
	}

	@Override
	public List<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		return ticketRepository.findAll();
		
	}

	@Override
	public Ticket getTicket(int ticketId) {
		// TODO Auto-generated method stub
		return ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with ticketid: " + ticketId));
	}

	@Override
	public double getPaidAmountForAllTickects() {
		// TODO Auto-generated method stub
		List<Ticket> allTickets=audienceRepository.getAllTickets();
		double amount=0.0;
		for(Ticket t:allTickets) {
			amount+=t.getTotalAmount();
		}
		
		return amount;
	}

}
