package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;

public interface IGroundService {
	public List<Match> getAllmatchesGround(int groundId);


	public Ground insertGround(Ground ground);

	public Ground updateGround(Ground ground);

	public Ground deleteGround(int groundId);

	public List<Match> getAllMatches();

	public Match getMatch(long matchId);
	
	public Ground getGround(int groundId);
}
