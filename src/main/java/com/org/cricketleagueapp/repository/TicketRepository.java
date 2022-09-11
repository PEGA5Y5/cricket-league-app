package com.org.cricketleagueapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Ticket;






public interface TicketRepository extends JpaRepository<Ticket, Integer> {


}
