package com.org.cricketleagueapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.exception.AudienceNotFoundException;
import com.org.cricketleagueapp.repository.AudienceRepository;

@Service
public class AudienceServiceImpl implements IAudienceService{

	@Autowired
	private AudienceRepository audienceRepository;
	
	String message = "Audience Not Found for id ";

	@Override
	public Audience getAudience(int audienceId) {
		return audienceRepository.findById(audienceId).orElseThrow(()-> new AudienceNotFoundException(message+audienceId));
	}

	@Override
	public Audience insertAudience(Audience audience) {
		return audienceRepository.save(audience);
	}

	@Override
	public Match getMatch(int audienceId) {
		Audience audience = audienceRepository.findById(audienceId).orElseThrow(()-> new AudienceNotFoundException(message+audienceId));
		return audience.getMatches();
	}

	@Override
	public Audience updateAudience(Audience audience) {
		int audienceId = audience.getAudienceId();
		Audience saveAudience = audienceRepository.findById(audienceId).orElseThrow(()-> new AudienceNotFoundException(message+audienceId));
		if(audience.getAudienceName() != null) {
			saveAudience.setAudienceName(audience.getAudienceName());
		}
		if(audience.getAmount() != 0) {
			saveAudience.setAmount(audience.getAmount());
		}
		if(audience.getMatches() != null) {
			saveAudience.setMatches(audience.getMatches());
		}
		if(audience.getTickets() != null) {
			saveAudience.setTickets(audience.getTickets());
		}
		return audienceRepository.save(saveAudience);
	}

	@Override
	public Audience deleteAudience(int audienceId) {
		Audience audience = audienceRepository.findById(audienceId).orElseThrow(()-> new AudienceNotFoundException(message+audienceId));
		audienceRepository.deleteById(audienceId);
		return audience;
	}

	@Override
	public List<Audience> getAllAudience() {
		return audienceRepository.findAll();
	}
}
