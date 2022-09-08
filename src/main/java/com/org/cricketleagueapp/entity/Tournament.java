package com.org.cricketleagueapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tournament")
public class Tournament {
	@Id
	@NotNull(message="tournament id cannot be null")
	@Column(name="tournament_id")
	private int tournamentId;
	
	@NotNull(message="tournament name cannot be null")
	@Column(name="tournament_name")
	private String tournamentName;
	
	@Column(name="prize_money")
	private double prizeMoney;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Organiser organiser;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tournament")
	@JsonIgnore
	private List<Match> matches;

	public Tournament() {
	}

	public Tournament(@NotNull(message = "tournament id cannot be null") int tournamentId,
			@NotNull(message = "tournament name cannot be null") String tournamentName, double prizeMoney,
			Organiser organiser, List<Match> matches) {
		super();
		this.tournamentId = tournamentId;
		this.tournamentName = tournamentName;
		this.prizeMoney = prizeMoney;
		this.organiser = organiser;
		this.matches = matches;
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
		this.matches = matches;
	}
}
