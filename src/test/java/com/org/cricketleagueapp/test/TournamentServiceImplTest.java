package com.org.cricketleagueapp.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
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

import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Organiser;
import com.org.cricketleagueapp.entity.Owner;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.repository.AudienceRepository;
import com.org.cricketleagueapp.repository.GroundRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;
import com.org.cricketleagueapp.repository.OrganiserRepository;
import com.org.cricketleagueapp.repository.OwnerRepository;
import com.org.cricketleagueapp.repository.PlayerRepository;
import com.org.cricketleagueapp.repository.TeamRepository;
import com.org.cricketleagueapp.repository.TicketRepository;
import com.org.cricketleagueapp.service.ITournamentService;

@RunWith(SpringRunner.class)
@SpringBootTest
class TournamentServiceImplTest {

	@Autowired
	private ITournamentService tournamentService;
	
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
	
	Tournament tournament1;
	
	Tournament tournament2;
	
	Tournament tournamentTest;
	
	List<Match> matchs = new ArrayList<>();
	
	List<Team> teams = new ArrayList<>();
	
	Ground ground;
	
	Organiser organiser;
	
	@BeforeEach
	void setUp() throws Exception {
		organiser = new Organiser();
		organiser.setOrganiserId(11);
		organiser.setOrganiserName("aa");
		organiserRepository.save(organiser);
		
		tournament1 = new Tournament();
		tournament1.setTournamentId(1);
		tournament1.setTournamentName("IPL");
		tournament1.setPrizeMoney(10000);
		tournament1.setOrganiser(organiser);
		tournament1.setStartDate(LocalDate.now());
		tournament1.setStartTime(LocalTime.now());
		tournament1.setEndTime(LocalTime.now());
		tournament1.setGroundId(0);
		
		tournamentTest = new Tournament();
		tournamentTest.setTournamentId(1);
		tournamentTest.setPrizeMoney(550000);
		
		tournament2 = new Tournament();
		tournament2.setTournamentId(2);
		tournament2.setTournamentName("BPL");
		tournament2.setPrizeMoney(20000);
		tournament2.setOrganiser(organiser);
		
		ground = new Ground();
		ground.setGroundId(1);
	}

	@Test
	void testGetTournament() {
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		assertEquals(1, tournamentService.getTournament(1).getTournamentId());
	}

	@Test
	void testGetAllTournaments() {
		when(tournamentRepository.findAll()).thenReturn(Stream.of(tournament1, tournament2).collect(Collectors.toList()));
		assertEquals(2, tournamentService.getAllTournaments().size());
	}

	@Test
	void testInsertTournament() {
		when(tournamentRepository.save(tournament1)).thenReturn(tournament1);
		assertEquals(1, tournamentService.insertTournament(tournament1).getTournamentId());
	}

	@Test
	void testUpdateTournament() {
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		when(tournamentRepository.save(tournament1)).thenReturn(tournament1);
		assertEquals(550000, tournamentService.updateTournament(tournamentTest).getPrizeMoney());
	}

	@Test
	void testDeleteTournament() {
		
		organiser.setTournaments(Stream.of(tournament1, tournament2).collect(Collectors.toList()));
		
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		
		when(groundRepository.findById(0)).thenReturn(Optional.of(ground));
		
		when(organiserRepository.findById(11)).thenReturn(Optional.of(organiser));
		
		tournamentService.deleteTournament(tournament1.getTournamentId());
		
        verify(tournamentRepository).deleteById(tournament1.getTournamentId());
	}

	@Test
	void testGetAllMatches() {
		List<Match> match = new ArrayList<>();
		match.add(new Match());
		match.add(new Match());
		match.add(new Match());
		tournament1.setMatches(match);
		tournament1.setTournamentId(1);
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		
		assertEquals(3, tournamentService.getAllMatches(1).size());
	}

	@Test
	void testGetMatch() {
		List<Match> match = new ArrayList<>();
		Match matchObj1 = new Match();
		matchObj1.setMatchId(0);
		tournament1.setMatches(match);
		tournament1.setTournamentId(1);
		matchObj1.setTournament(tournament1);
		
		Match matchObj2 = new Match();
		matchObj2.setMatchId(1);
		tournament1.setMatches(match);
		tournament1.setTournamentId(1);
		matchObj2.setTournament(tournament1);
		
		when(matchRepository.findAll()).thenReturn(Stream.of(matchObj1, matchObj2).collect(Collectors.toList()));
		
		assertEquals(0, tournamentService.getMatch(1, 0).getMatchId());
	}

	@Test
	void testGetTournamentMatch() {
		List<Match> match = new ArrayList<>();
		Match matchObj1 = new Match();
		matchObj1.setMatchId(0);
		tournament1.setMatches(match);
		tournament1.setTournamentId(1);
		matchObj1.setTournament(tournament1);
		
		long l = 0;
		
		when(matchRepository.findById(l)).thenReturn(Optional.of(matchObj1));
		
		assertEquals(1, tournamentService.getTournament(matchObj1).getTournamentId());
	}

	@Test
	void testAddTeamToTournament() {
		List<Team> teamsList = new ArrayList<>();
		Team team1 = new Team();
		team1.setTeamId(1);
		teamsList.add(team1);
		
		Team team2 = new Team();
		team2.setTeamId(2);
		teamsList.add(team2);
		
		Team team3 = new Team();
		team3.setTeamId(3);
		teamsList.add(team3);
		
		Team team = new Team();
		team.setTeamId(4);
		
		tournament1.setTeams(teamsList);
		
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		
		when(teamRepository.findById(4)).thenReturn(Optional.of(team));
		
		assertEquals(4, tournamentService.addTeamToTournament(1, 4).getTeamId());
		
		assertEquals(4, tournamentService.getTeams(1).size());
	}

	@Test
	void testGetTeams() {
		List<Team> teams = new ArrayList<>();
		Team team = new Team();
		team.setTeamId(1);
		teams.add(team);
		
		team = new Team();
		team.setTeamId(2);
		teams.add(team);
		
		team = new Team();
		team.setTeamId(3);
		teams.add(team);
		
		tournament1.setTeams(teams);
		
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		
		assertEquals(3, tournamentService.getTeams(1).size());
	}

	@Test
	void testDeleteTeamFromTournament() {
		List<Team> teams = new ArrayList<>();
		Team team = new Team();
		team.setTeamId(1);
		teams.add(team);
		
		team = new Team();
		team.setTeamId(2);
		teams.add(team);
		
		team = new Team();
		team.setTeamId(3);
		teams.add(team);
		
		tournament1.setTeams(teams);
		
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		
		assertEquals(2, tournamentService.deleteTeamFromTournament(1, 2).getTeamId());
	}

	@Test
	void testStartTournament() {
		tournament1.setEndTime(LocalTime.now());
		tournament1.setStartTime(LocalTime.now());
		tournament1.setStartDate(LocalDate.now());
		
		ground.setGroundId(6);
		tournament1.setGroundId(6);
		
		List<Team> teams = new ArrayList<>();
		Team team = new Team();
		team.setTeamId(1);
		teams.add(team);
		
		team = new Team();
		team.setTeamId(2);
		teams.add(team);
		
		team = new Team();
		team.setTeamId(3);
		teams.add(team);
		
		tournament1.setTeams(teams);
		
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		
		when(groundRepository.findById(6)).thenReturn(Optional.of(ground));
		
		when(tournamentRepository.save(tournament1)).thenReturn(tournament1);
		
		assertEquals(6, tournamentService.startTournament(1).getMatches().size());
	}

	@Test
	void testEndTournament() {
		Owner owner = new Owner();
		owner.setOwnerId(1);
		
		ground.setGroundId(6);
		tournament1.setGroundId(6);
		tournament1.setTournamentStatus("started");
		
		when(tournamentRepository.findById(1)).thenReturn(Optional.of(tournament1));
		
		when(ownerRepository.findById(1)).thenReturn(Optional.of(owner));
	
		when(groundRepository.findById(6)).thenReturn(Optional.of(ground));
		
		when(tournamentRepository.save(tournament1)).thenReturn(tournament1);
	
		assertTrue(tournamentService.endTournament(1, 1).getTournamentStatus().equalsIgnoreCase("ended"));
	}
}
