package com.org.cricketleagueapp.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Organiser;
import com.org.cricketleagueapp.entity.Tournament;



public interface IOrganiserService {

	public Organiser getOrganiser(int organiserId);

	public List<Organiser> getAllOrganisers();

	public Organiser insertOrganiser(Organiser organiser);

	public Organiser updateOrganiser(Organiser organiser);

	public List<Tournament> getTournaments(int organiserId);

	public Tournament getTournament(int tournamentId);

	public double getBudget(int organiserId);
	
	public Tournament setTournamentDateTime(int tournamentId, LocalDate startDate, LocalTime startTime, LocalTime endTime);
	
	public Tournament setTournamentGround(int tournamentId, int groundId);
	
	public Tournament setTournamentGround(int tournamentId);
	
	public Tournament setTournamentPrizeMoney(int tournamentId, double prizeMoney);
	
	public List<Ground> getAllGrounds();
}
