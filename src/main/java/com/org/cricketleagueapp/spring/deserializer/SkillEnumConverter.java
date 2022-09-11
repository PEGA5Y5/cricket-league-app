package com.org.cricketleagueapp.spring.deserializer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.org.cricketleagueapp.entity.Skill;

@Component
public class SkillEnumConverter implements Converter<String, Skill>{

	@Override
	public Skill convert(String source) {
		return Skill.of(Integer.valueOf(source));
	}

}
