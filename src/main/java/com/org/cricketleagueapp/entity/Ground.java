package com.org.cricketleagueapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="ground")
public class Ground {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int groundId;
	
	@Column(name="groundName")
	private String groundName;
	
	@Column(name="capacity")
	private int capacity;
	
	@OneToMany(mappedBy = "ground", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Match> match;
	
	@Column(name="ground_status")
	private boolean groundStatus = true; //if true ground can be booked and if false ground already booked
	
	@Embedded
	private Address address;

	public Ground() {
	}

	public int getGroundId() {
		return groundId;
	}

	public void setGroundId(int groundId) {
		this.groundId = groundId;
	}

	public String getGroundName() {
		return groundName;
	}
	

	public boolean isGroundStatus() {
		return groundStatus;
	}

	public void setGroundStatus(boolean groundStatus) {
		this.groundStatus = groundStatus;
	}

	public void setGroundName(String groundName) {
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

	public List<Match> getMatch() {
		return match;
	}

	public void setMatches(List<Match> match) {
		this.match = match;
	}

}

