package com.org.cricketleagueapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.exception.GroundNotFoundException;
import com.org.cricketleagueapp.exception.MatchNotFoundException;
import com.org.cricketleagueapp.repository.GroundRepository;
import com.org.cricketleagueapp.repository.MatchRepository;

@Service
public class GroundServiceImpl implements IGroundService{
	
	@Autowired
	private GroundRepository groundRepository;
	
	@Autowired
	private MatchRepository matchRepository;

	@Override
	public List<Match> getAllmatchesGround(long groundId) {
		//long longnum = Long.valueOf(groundId);
		List<Match> match=matchRepository.findAll();
		return match.stream().filter(p -> p.getGround().getGroundId() == groundId).collect(Collectors.toList());
	}

	
	@Override
	public Ground insertGround(Ground ground) {
		return groundRepository.save(ground);
	}
	 
	@Override
	public Ground updateGround(Ground ground) {
		int groundId = ground.getGroundId();
		Ground oldGround = getGround(groundId);
		Ground saveGround = new Ground();
		saveGround.setGroundId(groundId);
		if(ground.getGroundName() != null) {
			saveGround.setGroundName(ground.getGroundName());
		}else {
			saveGround.setGroundName(oldGround.getGroundName());
		}
		if(ground.getCapacity() != 0) {
			saveGround.setCapacity(ground.getCapacity());
		}else {
			saveGround.setCapacity(oldGround.getCapacity());
		}
		if(ground.getAddress() != null) {
			saveGround.setAddress(ground.getAddress());
		}else {
			saveGround.setAddress(oldGround.getAddress());
		}
		return groundRepository.save(saveGround);
	}
	
	@Override
	public Ground deleteGround(int groundId) {
	    Ground ground=groundRepository.findById(groundId).orElseThrow(() ->new GroundNotFoundException("Ground Not Found with id:"+groundId));
		groundRepository.deleteById(groundId);
		return ground;
	}
	
	@Override
	public List<Match> getAllMatches() {
		return matchRepository.findAll();
	}
	
	@Override
	public Match getMatch(long matchId) {
	
		return matchRepository.findById((long) matchId).orElseThrow(()-> new MatchNotFoundException("Match Not Found with id:"+matchId));
	}
	
	@Override
	public Ground getGround(int groundId) {
		return groundRepository.findById(groundId).orElseThrow(()-> new GroundNotFoundException("Ground Not Found for id "+groundId));
	}

}
