package com.org.cricketleagueapp.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Schedule;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.repository.AudienceRepository;
import com.org.cricketleagueapp.repository.MatchRepository;
import com.org.cricketleagueapp.repository.TeamRepository;
import com.org.cricketleagueapp.repository.TournamentRepository;
import com.org.cricketleagueapp.service.MatchServiceImpl;
import com.org.cricketleagueapp.service.TeamServiceImpl;
import com.org.cricketleagueapp.service.TournamentServiceImpl;

@SpringBootTest
class MatchServiceImplTest {
	
	@Autowired
	private MatchServiceImpl matchServiceImpl = new MatchServiceImpl();
	
	@MockBean
	private MatchRepository matchRepository;
	
	@MockBean
	private TeamRepository teamRepository;
	
	@MockBean
	private TournamentRepository tournamentRepository;
	
	@MockBean
	private AudienceRepository audienceRepository;
	
	Match match = new Match();
	Team team1 = new Team();
	Team team2 = new Team();
	Tournament tournament = new Tournament();
	Audience audience2 = new Audience();
	Set<Audience> audience = new HashSet<Audience>();

	@BeforeEach
	void setUp() throws Exception {
		team1.setTeamId(1);
		team1.setTeamName("Mumbai Indians");
		team2.setTeamId(2);
		team2.setTeamName("Chennai Super Kings");
		tournament.setTournamentId(3);
		tournament.setTournamentName("IPL");
		audience2.setAudienceId(4);
		audience2.setAudienceName("Rohit");
		audience.add(audience2);
		match.setMatchId(10);
		match.setTeam1(team1);
		match.setTeam2(team2);
		match.setTournament(tournament);
		match.setAudience(audience);
	}

	@Test
	void testInsertMatch() {	
		when(matchRepository.save(match)).thenReturn(match);
		Match match2 = matchServiceImpl.insertMatch(match);
		assertEquals(match.getMatchId(), match2.getMatchId());
	}
	
	@Test
	void testGetMatch() {
		Optional<Match> optionalMatch = Optional.of(match);
		when(matchRepository.findById(10L)).thenReturn(optionalMatch);
		Match match2 = matchServiceImpl.getMatch(10L);
		assertEquals(match.getMatchId(), match2.getMatchId());
	}

	@Test
	void testGetTeam1() {
		Optional<Match> optionalMatch = Optional.of(match);
		when(matchRepository.findById(match.getMatchId())).thenReturn(optionalMatch);
		Optional<Team> optionalTeam = Optional.of(team1);
		when(teamRepository.findById(match.getTeam1().getTeamId())).thenReturn(optionalTeam);
		Team team3 = matchServiceImpl.getTeam1(match.getMatchId());
		System.out.println(team3.getTeamId());
		assertEquals(match.getTeam1(), team3);
	}

	@Test
	void testGetTeam2() {
		Optional<Match> optionalMatch = Optional.of(match);
		when(matchRepository.findById(match.getMatchId())).thenReturn(optionalMatch);
		Optional<Team> optionalTeam = Optional.of(team2);
		when(teamRepository.findById(match.getTeam2().getTeamId())).thenReturn(optionalTeam);
		Team team3 = matchServiceImpl.getTeam2(match.getMatchId());
		assertEquals(match.getTeam2(), team3);
	}

	@Test
	void testGetTournament() {
		Optional<Match> optionalMatch = Optional.of(match);
		when(matchRepository.findById(match.getMatchId())).thenReturn(optionalMatch);
		Optional<Tournament> optionalTournament = Optional.of(tournament);
		when(tournamentRepository.findById(match.getTournament().getTournamentId())).thenReturn(optionalTournament);
		Tournament tournament2= matchServiceImpl.getTournament(match.getMatchId());
		assertEquals(match.getTournament(), tournament2);
	}

	@Test
	void testGetAllAudience() {
		Optional<Match> optionalMatch = Optional.of(match);
		when(matchRepository.findById(10L)).thenReturn(optionalMatch);
		Set<Audience> audiences= matchServiceImpl.getAllAudience(10L);
		assertEquals(match.getAudience(), audiences);
	}

	@Test
	void testGetSchedule() {
		Optional<Match> optionalMatch = Optional.of(match);
		when(matchRepository.findById(10L)).thenReturn(optionalMatch);
		Schedule schedule = matchServiceImpl.getSchedule(10L);
		System.out.println(schedule);
		assertEquals(match.getSchedule(), schedule);
	}

}
