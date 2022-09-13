package com.org.cricketleagueapp.entity;

import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="audience")
public class Audience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int audienceId;
	@Column(name="audienceName")
	private String audienceName;
	@Column(name="amount")
	private double amount;
  
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Match.class)
    @JoinColumn(name="matchId", referencedColumnName = "matchId", nullable = true)
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Match matches;
	
	
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="audienceId", referencedColumnName = "audienceId")
	private Set<Ticket> tickets;
    
	public Audience() {
	}

	public Audience(int audienceId, String audienceName, double amount, Match matches, Set<Ticket> tickets) {
		super();
		this.audienceId = audienceId;
		this.audienceName = audienceName;
		this.amount = amount;
		this.matches = matches;
		this.tickets = tickets;
	}

	public int getAudienceId() {
		return audienceId;
	}

	public void setAudienceId(int audienceId) {
		this.audienceId = audienceId;
	}

	public String getAudienceName() {
		return audienceName;
	}

	public void setAudienceName(String audienceName) {
		this.audienceName = audienceName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Match getMatches() {
		return matches;
	}

	public void setMatches(Match matches) {
		this.matches = matches;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

}
