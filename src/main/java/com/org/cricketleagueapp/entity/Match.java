package com.org.cricketleagueapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="match")
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long matchId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="team1")
	private Team team1;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="team2")
	private Team team2;
	 
	/*@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="tournament")*/
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Tournament.class)
	@JoinColumn(name="tournamentId", referencedColumnName = "tournamentId", nullable = true)
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Tournament tournament;
	
	@Embedded
	private Schedule schedule;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Ground.class)
	@JoinColumn(name="groundId", referencedColumnName = "groundId", nullable = true)
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Ground ground;
	
	private int availableSeats;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="matchId", referencedColumnName = "matchId")
	private Set<Audience> audience;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="matchId", referencedColumnName = "matchId")
	private Set<Ticket> ticket;
	
	public Match(long matchId, Team team1, Team team2, Tournament tournament, Schedule schedule, Ground ground,
			Set<Audience> audience, int availableSeats) {
		super();
		this.matchId = matchId;
		this.team1 = team1;
		this.team2 = team2;
		this.tournament = tournament;
		this.schedule = schedule;
		this.ground = ground;
		this.audience = audience;
		this.availableSeats = availableSeats;
	}


	public Match(Team team1, Team team2, Tournament tournament, Ground ground) {
		super();
		this.team1 = team1;
		this.team2 = team2;
		this.tournament = tournament;
		this.ground = ground;
	}


	public Set<Audience> getAudience() {
		return audience;
	}


	public void setAudience(Set<Audience> audience) {
		this.audience = audience;
	}

	public Ground getGround() {
		return ground;
	}


	public void setGround(Ground ground) {
		this.ground = ground;
	}


	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public Team getTeam2() {
		return team2;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Match(long matchId, Team team1) {
		super();
		this.matchId = matchId;
		this.team1 = team1;
	}

	public Match(long matchId) {
		super();
		this.matchId = matchId;
	}

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}
	
	public int getAvailableSeats() {
		return availableSeats;
	}


	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Match() {
		super();
	}
	
}
