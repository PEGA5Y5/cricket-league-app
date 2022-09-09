package com.org.cricketleagueapp.service;


import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;
import com.org.cricketleagueapp.exception.AudienceNotFoundException;
import com.org.cricketleagueapp.exception.TicketNotFoundException;
import com.org.cricketleagueapp.repository.AudienceRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.TicketRepository;

public class TicketServiceImpl implements ITicketService{
	
	private TicketRepository ticketRepository;
	
	private AudienceRepository audienceRepository;
	private MatchRepository matchRepository;

	@Override
	public Ticket getTicket(int ticketId) {
		return ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + ticketId));
	}

	@Override
	public Ticket insertTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}


	@Override
	public Ticket cancelTicket(int ticketId) {
		
	    Ticket t=ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + ticketId));
		ticketRepository.deleteById(ticketId);
		return t;
	}

	@Override
	public Match getMatch(int ticketId) {
		Ticket t1=ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + ticketId));
		
		
	}

	@Override
	public Audience getSchedule(int ticketId) {
		return audienceRepository.findById(ticketId).orElseThrow(() -> new AudienceNotFoundException("Audience not found with id: " + ticketId));
		
	}

	@Override
	public double getBill() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public double calculateBill(int noOfSeats) {
		// TODO Auto-generated method stub
		
		
	}
	

}
