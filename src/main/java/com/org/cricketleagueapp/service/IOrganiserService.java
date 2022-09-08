package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Organiser;
import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Tournament;



public interface IOrganiserService {

	public Organiser getOrganiser(int organiserId);

	public List<Organiser> getAllOrganisers();

	public Organiser insertOrganiser(Organiser organiser);

	public Organiser updateOrganiser(Organiser organiser);

	public List<Tournament> getTournaments(int organiserId);

	public Tournament getTournament(int tournamentId);

	public double payPrizeMoney(Owner owner, int tournamentId);

	public double getBudget(int organiserId);

}
