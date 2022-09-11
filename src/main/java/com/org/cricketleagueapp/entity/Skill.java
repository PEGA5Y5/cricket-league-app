package com.org.cricketleagueapp.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.org.cricketleagueapp.exception.UnknownEnumValueException;

public enum Skill {
	BATSMAN(1), BOWLER(2), WICKETKEEPER(3), ALLROUNDER(4);
	
	private int value;
	
	@JsonValue
	public int getValue() {
		return value;
	}
	
	@JsonCreator
	public static Skill of(Integer value) {
		if(value == null)
			return null;
		
		for(Skill item: Skill.values()) {
			if(value.equals(item.getValue())) {
				return item;
			}
		}
		
		throw new UnknownEnumValueException("GenderEnum: unknown value: " + value);
	}

	Skill(int value) {
		this.value = value;
	}
}
