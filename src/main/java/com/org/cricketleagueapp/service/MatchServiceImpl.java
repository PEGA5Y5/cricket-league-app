package com.org.cricketleagueapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Schedule;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.exception.MatchNotFoundException;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.TeamRepository;

@Service
public class MatchServiceImpl implements IMatchService{

	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private TeamServiceImpl teamServiceImpl;
	
//	@Autowired
//	private TournamentServiceImpl tournamentServiceImpl;
	
	@Autowired
	private AudienceServiceImpl audienceServiceImpl;
	
	public Match insertMatch(Match match) {
		return matchRepository.save(match);
	}

	@Override
	public Match getMatch(long matchId) {
		return matchRepository.findById(matchId).orElseThrow(()-> new MatchNotFoundException("Match Not Found for id "+matchId));
	}

	@Override
	public Match updateMatch(Match match, long id) {
		getMatch(id);
		match.setMatchId(id);
		return matchRepository.save(match);
	}

	@Override
	public Team getTeam1(long id) {
		Match matchTemp = matchRepository.findById(id).orElseThrow(()-> new MatchNotFoundException("Match Not Found for id "+id));
		return teamServiceImpl.getTeam(matchTemp.getTeam1().getTeamId());
	}
	
	@Override
	public Team getTeam2(long id) {
		Match matchTemp = matchRepository.findById(id).orElseThrow(()-> new MatchNotFoundException("Match Not Found for id "+id));
		return teamServiceImpl.getTeam(matchTemp.getTeam2().getTeamId());
	}
	
	public Map<String, Team> getTeams(long id){
		HashMap<String, Team> map = new HashMap<>();
		map.put("Team 1", getTeam1(id));
		map.put("Team 2", getTeam2(id));
		return map;
	}

//	@Override
//	public Tournament getTournament(long matchId) {
//		Match matchTemp = matchRepository.findById(matchId).orElseThrow(()-> new MatchNotFoundException("Match Not Found for id "+matchId));
//		return tournamentServiceImpl.getTournament(matchTemp.getTournament().getTournamentId());
//	}

	@Override
	public Set<Audience> getAllAudience(long matchId) {
		Match matchTemp = matchRepository.findById(matchId).orElseThrow(()-> new MatchNotFoundException("Match Not Found for id "+matchId));
		return matchTemp.getAudience();
	}

	@Override
	public Schedule getSchedule(long matchId) {
		Match matchTemp = matchRepository.findById(matchId).orElseThrow(()-> new MatchNotFoundException("Match Not Found for id "+matchId));
		return matchTemp.getSchedule();
	}

	@Override
	public Audience getAudience(int audienceId) {
		return audienceServiceImpl.getAudience(audienceId);
	}
}
