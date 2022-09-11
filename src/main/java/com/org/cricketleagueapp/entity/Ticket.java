package com.org.cricketleagueapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
	private int ticketId;
    @Column(name="ticketName")
	private String ticketName;
    @Column(name="totalAmount")
	private double totalAmount;
    @Column(name="noOfSeats")
	private int noOfSeats;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Match.class)
    @JoinColumn(name="matchId", referencedColumnName = "matchId", nullable = true)
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Match match;
    
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Audience.class)
    @JoinColumn(name="audienceId", referencedColumnName = "audienceId", nullable = true)
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Audience audience;

	public Ticket() {
	}

	 public Ticket(int ticketId, String ticketName, double totalAmount, int noOfSeats, Match match, Audience audience) {
			super();
			this.ticketId = ticketId;
			this.ticketName = ticketName;
			this.totalAmount = totalAmount;
			this.noOfSeats = noOfSeats;
			this.match = match;
			this.audience = audience;
		}
	 
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Audience getAudience() {
		return audience;
	}

	public void setAudience(Audience audience) {
		this.audience = audience;
	}

}
