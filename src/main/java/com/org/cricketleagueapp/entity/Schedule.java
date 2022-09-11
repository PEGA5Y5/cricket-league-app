package com.org.cricketleagueapp.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Embeddable
public class Schedule {
	
	
	private LocalDate matchDate;
	private LocalTime startTime;
	private LocalTime endTime;


	public Schedule() {
		super();
	}

	public Schedule(LocalDate matchDate, LocalTime startTime, LocalTime endTime) {
		super();
		this.matchDate = matchDate;
		this.startTime = startTime;
		this.endTime = endTime;
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
