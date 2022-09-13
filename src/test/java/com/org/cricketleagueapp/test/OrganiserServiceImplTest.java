package com.org.cricketleagueapp.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.org.cricketleagueapp.entity.Organiser;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.repository.AudienceRepository;
import com.org.cricketleagueapp.repository.GroundRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.OrganiserRepository;
import com.org.cricketleagueapp.repository.OwnerRepository;
import com.org.cricketleagueapp.repository.PlayerRepository;
import com.org.cricketleagueapp.repository.TeamRepository;
import com.org.cricketleagueapp.repository.TicketRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;
import com.org.cricketleagueapp.service.IOrganiserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrganiserServiceImplTest {

	@Autowired
	private IOrganiserService organiserService;
	
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
	
	Organiser organiser1;
	
	Organiser organiser2;
	
	Organiser organiser3;
	
	Organiser organiserTest;
	
	Ground ground1;
	
	Ground ground2;
	
	Tournament tournament1;
	
	Tournament tournament2;
	
	List<Tournament> tournamentList;
	
	@BeforeEach
	void setUp() throws Exception {
		
		tournamentList = new ArrayList<>();
		
		tournament1 = new Tournament();
		tournament1.setTournamentId(1);
		tournament1.setTournamentName("IPL");
		tournament1.setPrizeMoney(10000);
		tournament1.setStartDate(LocalDate.now());
		tournament1.setStartTime(LocalTime.now());
		tournament1.setEndTime(LocalTime.now());
		tournament1.setGroundId(1);
		
		tournamentList.add(tournament1);
		
		tournament2 = new Tournament();
		tournament2.setTournamentId(2);
		tournament2.setTournamentName("BPL");
		tournament2.setPrizeMoney(20000);
		
		tournamentList.add(tournament2);
		
		ground1 = new Ground();
		ground1.setCapacity(21);
		ground1.setGroundId(1);
		ground1.setGroundStatus(true);
		
		ground2 = new Ground();
		ground2.setCapacity(90);
		ground2.setGroundId(2);
		
		organiser1 = new Organiser();
		organiser1.setOrganiserId(1);
		organiser1.setBudget(10000);
		organiser1.setTournaments(tournamentList);
		
		organiserTest = new Organiser();
		organiserTest.setOrganiserId(1);
		organiserTest.setBudget(550000);
		
		organiser2 = new Organiser();
		organiser2.setOrganiserId(2);
		organiser2.setBudget(20000);
		
		organiser3 = new Organiser();
		organiser3.setOrganiserId(3);
		organiser3.setBudget(30000);	
	}

	@Test
	void testGetOrganiser() {
		when(organiserRepository.findById(1)).thenReturn(Optional.of(organiser1));
		assertEquals(10000, organiserService.getOrganiser(1).getBudget());
	}

	@Test
	void testGetAllOrganisers() {
		when(organiserRepository.findAll()).thenReturn(Stream.of(organiser1, organiser2).collect(Collectors.toList()));
		assertEquals(2, organiserService.getAllOrganisers().size());
	}

	@Test
	void testInsertOrganiser() {
		when(organiserRepository.findAll()).thenReturn(Stream.of(organiser1, organiser2).collect(Collectors.toList()));
		when(organiserRepository.save(organiser3)).thenReturn(organiser3);
		assertEquals(3, organiserService.insertOrganiser(organiser3).getOrganiserId());
	}

	@Test
	void testUpdateOrganiser() {
		when(organiserRepository.findById(1)).thenReturn(Optional.of(organiser1));
		when(organiserRepository.save(organiser1)).thenReturn(organiser1);
		assertEquals(550000, organiserService.updateOrganiser(organiserTest).getBudget());
	}

	@Test
	void testGetTournaments() {
		when(organiserRepository.findById(1)).thenReturn(Optional.of(organiser1));
		assertEquals(2, organiserService.getTournaments(1).size());
	}

	@Test
	void testGetTournament() {
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		assertEquals(10000, organiserService.getTournament(1).getPrizeMoney());
	}

	@Test
	void testGetBudget() {
		when(organiserRepository.findById(1)).thenReturn(Optional.of(organiser1));
		assertEquals(10000, organiserService.getBudget(1));
	}

	@Test
	void testSetTournamentDateTime() {
		LocalDate date = LocalDate.now();
		LocalTime startTime = LocalTime.now();
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		when(tournamentRepository.save(tournament1)).thenReturn(tournament1);
		assertTrue(date.isEqual(organiserService.setTournamentDateTime(1, date, startTime, startTime).getStartDate()));
	}

	@Test
	void testSetTournamentGroundIntInt() {
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		when(groundRepository.findById(1)).thenReturn(Optional.of(ground1));
		when(groundRepository.save(ground1)).thenReturn(ground1);
		when(tournamentRepository.save(tournament1)).thenReturn(tournament1);
		organiserService.setTournamentGround(1, 1);
		assertFalse(ground1.isGroundStatus());
	}

	@Test
	void testSetTournamentPrizeMoney() {
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		when(tournamentRepository.save(tournament1)).thenReturn(tournament1);
		assertEquals(56000, organiserService.setTournamentPrizeMoney(1, 56000).getPrizeMoney());
	}

	@Test
	void testGetAllGrounds() {
		when(groundRepository.findAll()).thenReturn(Stream.of(ground1, ground2).collect(Collectors.toList()));
		assertEquals(2, organiserService.getAllGrounds().size());
	}

}
