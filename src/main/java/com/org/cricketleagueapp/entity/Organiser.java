package com.org.cricketleagueapp.entity;

public class Organiser {

	private int organiserId;
	private String organiserName;
	private String email;
	private long phone;
	private double payment;
	private double budget;

	private Tournament tournaments;

	public Organiser() {
		// TODO Auto-generated constructor stub
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

	public Tournament getTournaments() {
		return tournaments;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public void setTournaments(Tournament tournaments) {
		this.tournaments = tournaments;
	}

}
