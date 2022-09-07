package com.org.cricketleagueapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Organiser;
import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Tournament;


public interface OrganiserRepository extends JpaRepository<Organiser, Integer> {
	public Organiser getOrganiser(int organiserId);

	public List<Organiser> getAllOrganisers();

	public Organiser insertOrganiser(Organiser organiser);

	public Organiser updateOrganiser(Organiser organiser);

	public List<Tournament> getTournaments();

	public Tournament getTournament(int tournamentId);

	public double payPrizeMoney(Owner owner);

	public double getBudget();
}
