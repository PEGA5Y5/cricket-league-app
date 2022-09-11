package com.org.cricketleagueapp.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tournament")
public class Tournament {
	@Id
	//@Column(name="tournament_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int tournamentId;
	
	@NotNull(message="tournament name cannot be null")
	@Column(name="tournament_name")
	private String tournamentName;
	
	@Column(name="prize_money")
	private double prizeMoney;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="start_time")
	private LocalTime startTime; 
	
	@Column(name="end_time")
	private LocalTime endTime;
	
	@Column(name="ground_id")
	private int groundId;
	
	@Column(name="tournament_status")
	private String tournamentStatus = "not-started";

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Organiser organiser;

	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tournament")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="tournamentId", referencedColumnName = "tournamentId")
	private List<Match> matches;
	
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tournament")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="tournamentId", referencedColumnName = "tournamentId")
	private Set<Team> teams = new HashSet<>();
	
	public Tournament(@NotNull(message = "tournament id cannot be null") int tournamentId,
			@NotNull(message = "tournament name cannot be null") String tournamentName, double prizeMoney,
			LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int groundId,
			String tournamentStatus, Organiser organiser, List<Match> matches, List<Team> teams) {
		super();
		this.tournamentId = tournamentId;
		this.tournamentName = tournamentName;
		this.prizeMoney = prizeMoney;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.groundId = groundId;
		this.tournamentStatus = tournamentStatus;
		this.organiser = organiser;
		this.matches = matches;
		this.teams = new HashSet<>(teams);
	}
	
	public Tournament() {
	}
	
	public String getTournamentStatus() {
		return tournamentStatus;
	}

	public void setTournamentStatus(String tournamentStatus) {
		this.tournamentStatus = tournamentStatus;
	}

	public List<Team> getTeams() {
		return teams.stream().collect(Collectors.toList());
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams.stream().collect(Collectors.toSet());
	}

	public int getGroundId() {
		return groundId;
	}

	public void setGroundId(int groungId) {
		this.groundId = groungId;
	}

	public int getTournamentId() {
		return tournamentId;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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
