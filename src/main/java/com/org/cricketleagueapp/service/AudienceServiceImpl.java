package com.org.cricketleagueapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.exception.AudienceNotFoundException;
import com.org.cricketleagueapp.exception.MatchNotFoundException;
import com.org.cricketleagueapp.exception.TournamentNotFoundException;
import com.org.cricketleagueapp.repository.AudienceRepository;
import com.org.cricketleagueapp.repository.TeamRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;

@Service
public class AudienceServiceImpl implements IAudienceService{

	@Autowired
	private AudienceRepository audienceRepository;

	@Override
	public Audience getAudience(int audienceId) {
		return audienceRepository.findById(audienceId).orElseThrow(()-> new AudienceNotFoundException("Audience Not Found for id "+audienceId));
	}

	@Override
	public Audience insertAudience(Audience audience) {
		return audienceRepository.save(audience);
	}

	@Override
	public Match getMatch(int audienceId) {
		Audience audience = getAudience(audienceId);
		Match match = audience.getMatches();
		return match;
	}

	@Override
	public Audience updateAudience(Audience audience) {
		int audienceId = audience.getAudienceId();
		Audience oldAudience = getAudience(audienceId);
		Audience saveAudience = new Audience();
		saveAudience.setAudienceId(audienceId);
		if(audience.getAudienceName() != null) {
			saveAudience.setAudienceName(audience.getAudienceName());
		}else {
			saveAudience.setAudienceName(oldAudience.getAudienceName());
		}
		if(audience.getAmount() != 0) {
			saveAudience.setAmount(audience.getAmount());
		}else {
			saveAudience.setAmount(oldAudience.getAmount());
		}
		if(audience.getMatches() != null) {
			saveAudience.setMatches(audience.getMatches());
		}else {
			saveAudience.setMatches(oldAudience.getMatches());
		}
		if(audience.getTickets() != null) {
			saveAudience.setTickets(audience.getTickets());
		}else {
			saveAudience.setTickets(oldAudience.getTickets());
		}
		return audienceRepository.save(saveAudience);
	}

	@Override
	public Audience deleteAudience(int audienceId) {
		Audience audience = getAudience(audienceId);
		audienceRepository.deleteById(audienceId);
		return audience;
	}

	@Override
	public List<Audience> getAllAudience() {
		return audienceRepository.findAll();
	}
}
