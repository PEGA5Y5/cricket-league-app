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
public class IGroundServiceImp implements IGroundService{
	private GroundRepository groundRepository;
	@Autowired
	MatchRepository matchRepository;
	   public IGroundServiceImp(GroundRepository groundRepository) {
			super();
			this.groundRepository = groundRepository;
		}
	   
	@Override
	public List<Match> getAllmatchesGround(int groundId) {
		long longnum = Long.valueOf(groundId);
		List<Match> match=matchRepository.findAll();
		return match.stream().filter(p -> p.getGround().getGroundId() == longnum).collect(Collectors.toList());
	}
	

	
	 @Override
	   public Ground insertGround(Ground ground) {
		   return groundRepository.save(ground);
	   }
	 
	@Override
	public Ground updateGround(Ground ground) {
		//long id=ground.getGroundId();
		return groundRepository.save(ground);
	}
	
	@Override
	public Ground deleteGround(int groundId) {
		Ground ground=groundRepository.findById(groundId).orElseThrow(() ->new GroundNotFoundException("Ground Not Found with id:"+groundId));
		 groundRepository.deleteById(groundId);
		 return ground;
	}
	@Override
	public List<Match> getAllMatches() {
		// TODO Auto-generated method stub
		return matchRepository.findAll();
	}
	@Override
	public Match getMatch(int matchId) {
		// TODO Auto-generated method stub
		return matchRepository.findById((long) matchId).orElseThrow(()-> new MatchNotFoundException("Match Not Found with id:"+matchId));
	
		
	}
	   
}
   

