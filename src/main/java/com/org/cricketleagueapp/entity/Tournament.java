package com.org.cricketleagueapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="tournament")
public class Tournament {
	@Id
	@Column(name="tournament_id")
	private int tournamentId;
	@Column(name="tournament_name")
	private String tournamentName;
	@Column(name="prize_money")
	private double prizeMoney;

	@ManyToOne(fetch = FetchType.LAZY)
	private Organiser organiser;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tournament")
	private List<Match> matches;

	public Tournament() {
	}

	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	public Organiser getOrganiser() {
		return organiser;
	}

	public void setOrganiser(Organiser organiser) {
		this.organiser = organiser;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}
	
	public double getPrizeMoney() {
		return prizeMoney;
	}

	public void setPrizeMoney(double prizeMoney) {
		this.prizeMoney = prizeMoney;
	}
	
	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		if(this.matches.isEmpty()) {
			this.matches = new ArrayList<>();
			this.matches.addAll(matches);
		}
		else {
			this.matches.addAll(matches);
		}
	}
}
