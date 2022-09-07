package com.org.cricketleagueapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;


public interface AudienceRepository extends JpaRepository<Audience, Integer> {

	public Audience getAudience(int audienceId);

	public Audience insertAudience(Audience audience);

	public Match getMatch(int audienceId);

	public List<Match> getAllMatches();

	public List<Ticket> getAllTickets();

	public Ticket getTicket(int ticketId);

	public double getPaidAmountForAllTickects();
}
