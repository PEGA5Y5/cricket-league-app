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

	@Override
	public Ticket getTicket(int ticketId) {
		// TODO Auto-generated method stub
		return ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + ticketId));
	}

	@Override
	public Ticket insertTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketRepository.save(ticket);
	}


	@Override
	public Ticket cancelTicket(int ticketId) {
		// TODO Auto-generated method stub
	    
		
		Ticket t=ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + ticketId));
		ticketRepository.deleteById(ticketId);
		return t;
	}

	@Override
	public Match getMatch() {
		// TODO Auto-generated method stub
		return ticketRepository.getMatch();
	}

	@Override
	public Audience getSchedule(int ticketId) {
		// TODO Auto-generated method stub
		return audienceRepository.findById(ticketId).orElseThrow(() -> new AudienceNotFoundException("Audience not found with id: " + ticketId));
		
	}

	@Override
	public double getBill() {
		// TODO Auto-generated method stub
		return ticketRepository.getBill();
		
	}

	@Override
	public double calculateBill(int noOfSeats) {
		// TODO Auto-generated method stub
		return ticketRepository.calculateBill(noOfSeats);
	}
	

}
