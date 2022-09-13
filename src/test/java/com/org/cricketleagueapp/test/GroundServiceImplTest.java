package com.org.cricketleagueapp.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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

import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.repository.GroundRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.OrganiserRepository;
import com.org.cricketleagueapp.repository.TeamRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;
import com.org.cricketleagueapp.service.GroundServiceImpl;
import com.org.cricketleagueapp.service.IGroundService;
import com.org.cricketleagueapp.service.IOrganiserService;
import com.org.cricketleagueapp.service.ITournamentService;

@RunWith(SpringRunner.class)
@SpringBootTest
class GroundServiceImplTest {

	@Autowired
	private ITournamentService tournamentService;
	
	@Autowired
	private IOrganiserService organiserService;
	
	@Autowired
	private IGroundService groundService;
	
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

	Ground ground1;
	
	Ground ground2;
	
	Ground ground3;
	
	List<Match> match1;
	
	List<Match> match2;
	
	Match matchTest;
	
	@BeforeEach
	void setUp() throws Exception {
		match1 = new ArrayList<>();
		
		ground1 = new Ground();
		ground1.setCapacity(21);
		ground1.setGroundId(11);
		ground1.setMatches(match1);
		
		ground2 = new Ground();
		ground2.setCapacity(90);
		ground2.setGroundId(22);
		ground2.setMatches(match2);
		
		Match matchTemp = new Match();
		matchTemp.setMatchId(1);
		matchTemp.setGround(ground1);
		match1.add(matchTemp);
		
		matchTemp = new Match();
		matchTemp.setMatchId(2);
		matchTemp.setGround(ground1);
		
		match1.add(matchTemp);
		
		matchTemp = new Match();
		matchTemp.setMatchId(3);
		matchTemp.setGround(ground1);
		match1.add(matchTemp);
		
		List<Match> match2 = new ArrayList<>();
		
		matchTemp = new Match();
		matchTemp.setMatchId(4);
		match2.add(matchTemp);
		
		matchTemp = new Match();
		matchTemp.setMatchId(5);
		match2.add(matchTemp);
		
		matchTemp = new Match();
		matchTemp.setMatchId(6);
		match2.add(matchTemp);	
			
		matchTest = new Match();
		matchTest.setMatchId(19);
		matchTest.setGround(ground1);
		
		ground3 = new Ground();
		ground3.setCapacity(90);
		ground3.setGroundId(11);
		ground3.setMatches(match2);
	}

	@Test
	void testGetAllmatchesGround() {
		when(matchRepository.findAll()).thenReturn(match1);
		assertEquals(3, groundService.getAllmatchesGround(11).size());
	}

	@Test
	void testInsertGround() {
		when(groundRepository.save(ground1)).thenReturn(ground1);
		assertEquals(11, groundService.insertGround(ground1).getGroundId());
	}

	@Test
	void testUpdateGround() {
		when(groundRepository.findById(11)).thenReturn(Optional.of(ground1));
		when(groundRepository.save(ground1)).thenReturn(ground1);
		assertEquals(90, groundService.updateGround(ground3).getCapacity());
	}

	@Test
	void testDeleteGround() {
		when(groundRepository.findById(11)).thenReturn(Optional.of(ground1));
		groundService.deleteGround(11);
        verify(groundRepository).deleteById(11);
	}

	@Test
	void testGetAllMatches() {
		when(matchRepository.findAll()).thenReturn(match1);
		assertEquals(3, match1.size());
	}

	@Test
	void testGetMatch() {
		when(matchRepository.findById((long) 19)).thenReturn(Optional.of(matchTest));
		assertTrue(matchTest instanceof Match);
	}

	@Test
	void testGetGround() {
		when(groundRepository.findById(11)).thenReturn(Optional.of(ground1));
		assertEquals(11, groundService.getGround(11).getGroundId());
	}

}
