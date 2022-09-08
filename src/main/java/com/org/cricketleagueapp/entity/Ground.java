package com.org.cricketleagueapp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ground")
public class Ground {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long groundId;
	@Column(name="groundname")
	private String groundName;
	@Column(name="capacity")
	private int capacity;
	@OneToOne(cascade=CascadeType.ALL)
	private Match matches;
	@Embedded
	private Address address;

	public Ground() {
	}

	public long getGroundId() {
		return groundId;
	}

	public void setGroundId(long groundId) {
		this.groundId = groundId;
	}

	public String getgroundName() {
		return groundName;
	}

	public void setgroundName(String groundName) {
		this.groundName = groundName;
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
