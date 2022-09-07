package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;



public interface IAudienceService {

	public Audience getAudience(int audienceId);

	public Audience insertAudience(Audience audience);

	public Match getMatch(int audienceId);

	public List<Match> getAllMatches();

	public List<Ticket> getAllTickets();

	public Ticket getTicket(int ticketId);

	public double getPaidAmountForAllTickects();

}
