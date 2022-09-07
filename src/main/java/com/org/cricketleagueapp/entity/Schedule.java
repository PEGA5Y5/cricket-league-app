package com.org.cricketleagueapp.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embeddable;

@Embeddable
public class Schedule {

	private LocalDate matchDate;
	private LocalTime startTime;
	private LocalTime endTime;

	public Schedule() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
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

}
