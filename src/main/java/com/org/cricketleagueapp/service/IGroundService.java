package com.org.cricketleagueapp.service;

import java.util.List;

import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;



public interface IGroundService {

	public List<Match> getAllmatchesGround(int groungId);

	//public Match getmatch(int groungId, int matchId);

	public Ground insertGround(Ground ground);

	public Ground updateGround(Ground ground);

	public Ground deleteGround(int groungId);

	public List<Match> getAllMatches();

	public Match getMatch(int matchId);

}
