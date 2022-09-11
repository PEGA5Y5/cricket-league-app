package com.org.cricketleagueapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;
import com.org.cricketleagueapp.exception.AudienceNotFoundException;
import com.org.cricketleagueapp.exception.InsufficientBalanceException;
import com.org.cricketleagueapp.exception.SeatNotAvailable;
import com.org.cricketleagueapp.exception.TicketNotFoundException;
import com.org.cricketleagueapp.repository.AudienceRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.TicketRepository;

@Service
public class TicketServiceImpl implements ITicketService{
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private AudienceServiceImpl audienceService;
	
	@Autowired
	private MatchServiceImpl matchService;

	@Override
	public Ticket getTicket(int ticketId) {
		return ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + ticketId));
	}

	@Override
	public Ticket insertTicket(Ticket ticket, int audienceId) {
		Audience audience = audienceService.getAudience(audienceId);
		int noOfSeats = ticket.getNoOfSeats();
		long matchId = ticket.getMatch().getMatchId();
		double pay = calculateBill(noOfSeats, matchId);
		ticket.setTotalAmount(pay);
		if(ticket.getTotalAmount() > audience.getAmount()) {
			throw new InsufficientBalanceException("Not Sufficient Balance");
		}
		Match match = matchService.getMatch(matchId);
		System.out.println(match.getAvailableSeats());
		match.setAvailableSeats(match.getAvailableSeats() - noOfSeats);
		matchService.updateMatch(match);
		audience.setAmount(audience.getAmount() - pay);
		ticket.setAudience(audience);
		audienceService.updateAudience(audience);
		return ticketRepository.save(ticket);
	}


	@Override
	public Ticket cancelTicket(int ticketId) {
		Ticket ticket= getTicket(ticketId);
		double refund = ticket.getTotalAmount();
		Audience audience = ticket.getAudience();
		audience.setAmount(audience.getAmount() + refund);
		ticketRepository.deleteById(ticketId);
		return ticket;
	}

	@Override
	public Match getMatch(int ticketId) {
		Ticket ticket = getTicket(ticketId);
		return ticket.getMatch();
	}


	@Override
	public double calculateBill(int noOfSeats,long matchId) {
		Match match = matchService.getMatch(matchId);
		int availSeat = match.getAvailableSeats() - noOfSeats;
		if(match.getAvailableSeats()< 0) {
			throw new SeatNotAvailable("Not enough seats");
		}else {
			return (double) noOfSeats * 500;
		}
	}

	@Override
	public Ticket updateTicket(Ticket ticket) {
		int ticketId = ticket.getTicketId();
		Ticket oldTicket = getTicket(ticketId);
		Ticket saveTicket = new Ticket();
		saveTicket.setTicketId(ticketId);
		saveTicket.setTicketName(oldTicket.getTicketName());
		saveTicket.setTotalAmount(oldTicket.getTotalAmount());
		saveTicket.setMatch(oldTicket.getMatch());
		if(ticket.getNoOfSeats() != 0) {
			saveTicket.setNoOfSeats(ticket.getNoOfSeats());
		}else {
			saveTicket.setNoOfSeats(oldTicket.getNoOfSeats());
		}
		return ticketRepository.save(saveTicket);
	}

}
