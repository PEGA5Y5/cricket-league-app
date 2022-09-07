package com.org.cricketleagueapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;




public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	public Ticket getTicket(int ticketId);

	public Ticket insertTicket(Ticket ticket);

	public Ticket cancelTicket(int ticketId);

	public Match getMatch();

	public Audience getSchedule(int ticketId);

	public double getBill();

	public double calculateBill(int noOfSeats);

}
