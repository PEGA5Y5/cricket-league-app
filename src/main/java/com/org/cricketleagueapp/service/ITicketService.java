package com.org.cricketleagueapp.service;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;

public interface ITicketService {

	public Ticket getTicket(int ticketId);

	public Ticket insertTicket(Ticket ticket,int audienceId);

	public Ticket cancelTicket(int ticketId);

	public Match getMatch(int ticketId);

	public double calculateBill(int noOfSeats, long matchId);
	
	public Ticket updateTicket(Ticket ticket);

}
