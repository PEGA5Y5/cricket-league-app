package com.org.cricketleagueapp.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.repository.AudienceRepository;
import com.org.cricketleagueapp.repository.GroundRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.OrganiserRepository;
import com.org.cricketleagueapp.repository.OwnerRepository;
import com.org.cricketleagueapp.repository.PlayerRepository;
import com.org.cricketleagueapp.repository.TeamRepository;
import com.org.cricketleagueapp.repository.TicketRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;
import com.org.cricketleagueapp.service.IAudienceService;

@RunWith(SpringRunner.class)
@SpringBootTest
class AudienceServiceImplTest {

	@Autowired
	private IAudienceService audienceService;
	
	@MockBean
	private TournamentRepository tournamentRepository;
	
	@MockBean
	private GroundRepository groundRepository;
	
	@MockBean
	private TeamRepository teamRepository;
	
	@MockBean
	private MatchRepository matchRepository;
	
	@MockBean
	private OrganiserRepository organiserRepository;
	
	@MockBean
	private AudienceRepository audienceRepository;
	
	@MockBean
	private OwnerRepository ownerRepository;
	
	@MockBean
	private PlayerRepository playerRepository;
	
	@MockBean
	private TicketRepository ticketRepository;
	
	Audience audience1;
	
	Audience audience2;
	
	Audience audience3;
	
	Match match;
	
	@BeforeEach
	void setUp() throws Exception {
		audience1 = new Audience();
		audience2 = new Audience();
		audience3 = new Audience();
		match = new Match();
		
		match.setMatchId(1);
		
		audience1.setAudienceId(1);
		audience1.setMatches(match);
		audience1.setAmount(60000);
		audience2.setAudienceId(2);
		audience3.setAudienceId(1);
		audience3.setAmount(50000);
	}

	@Test
	void testGetAudience() {
		when(audienceRepository.findById(1)).thenReturn(Optional.of(audience1));
		assertEquals(60000, audienceService.getAudience(1).getAmount());
	}

	@Test
	void testInsertAudience() {
		when(audienceRepository.save(audience1)).thenReturn(audience1);
		assertEquals(1, audienceService.insertAudience(audience1).getAudienceId());
	}

	@Test
	void testGetMatch() {
		when(audienceRepository.findById(1)).thenReturn(Optional.of(audience1));
		assertEquals(1, audienceService.getMatch(1).getMatchId());
	}

	@Test
	void testUpdateAudience() {
		when(audienceRepository.findById(1)).thenReturn(Optional.of(audience1));
		when(audienceRepository.save(audience1)).thenReturn(audience1);
		assertEquals(50000, audienceService.updateAudience(audience3).getAmount());
	}

	@Test
	void testDeleteAudience() {
		when(audienceRepository.findById(1)).thenReturn(Optional.of(audience1));
		audienceRepository.deleteById(1);
		verify(audienceRepository).deleteById(1);
	}

	@Test
	void testGetAllAudience() {
		when(audienceRepository.findAll()).thenReturn(Stream.of(audience1, audience2).collect(Collectors.toList()));
		assertEquals(2, audienceService.getAllAudience().size());
	}

}
