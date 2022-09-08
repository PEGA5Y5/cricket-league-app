package com.org.cricketleagueapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="organiser")
public class Organiser {
	
	@Id
	@NotNull(message="organiser id cannot be null")
	@Column(name="organiser_id")
	private int organiserId;
	
	@NotNull(message="organiser name cannot be null")
	@Column(name="organiser_name")
	private String organiserName;
	
	@Email(message="Email should be valid")
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private long phone;
	
	@Column(name="payment")
	private double payment;
	
	@Column(name="budget")
	private double budget;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organiser")
	@JsonIgnore
	private List<Tournament> tournaments;

	public Organiser(@NotNull(message = "organiser id cannot be null") int organiserId,
			@NotNull(message = "organiser name cannot be null") String organiserName,
			@Email(message = "Email should be valid") String email, long phone, double payment, double budget,
			List<Tournament> tournaments) {
		super();
		this.organiserId = organiserId;
		this.organiserName = organiserName;
		this.email = email;
		this.phone = phone;
		this.payment = payment;
		this.budget = budget;
		this.tournaments = tournaments;
	}

	public Organiser() {
	}

	public int getOrganiserId() {
		return organiserId;
	}

	public void setOrganiserId(int organiserId) {
		this.organiserId = organiserId;
	}

	public String getOrganiserName() {
		return organiserName;
	}

	public void setOrganiserName(String organiserName) {
		this.organiserName = organiserName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public List<Tournament> getTournaments() {
		return tournaments;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public void setTournaments(List<Tournament> list) {
		this.tournaments = list;
	}

}
