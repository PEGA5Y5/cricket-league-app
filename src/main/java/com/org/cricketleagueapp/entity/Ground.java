package com.org.cricketleagueapp.entity;

public class Ground {
	private long groundId;
	private String groungName;
	private int capacity;

	private Match matches;

	private Address address;

	public Ground() {
	}

	public long getGroundId() {
		return groundId;
	}

	public void setGroundId(long groundId) {
		this.groundId = groundId;
	}

	public String getGroungName() {
		return groungName;
	}

	public void setGroungName(String groungName) {
		this.groungName = groungName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Match getMatches() {
		return matches;
	}

	public void setMatches(Match matches) {
		this.matches = matches;
	}

}
