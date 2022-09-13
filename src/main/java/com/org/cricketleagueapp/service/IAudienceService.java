package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;


public interface IAudienceService {
	public Audience getAudience(int audienceId);
	public Audience insertAudience(Audience audience);
	public Audience updateAudience(Audience audience);
	public Audience deleteAudience(int audienceId);
	public Match getMatch(int audienceId);
	public List<Audience> getAllAudience();
}
