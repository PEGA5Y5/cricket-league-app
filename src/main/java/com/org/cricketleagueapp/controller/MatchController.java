package com.org.cricketleagueapp.controller;

//import java.util.List;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.MatchDummy;
import com.org.cricketleagueapp.entity.Schedule;
import com.org.cricketleagueapp.entity.Team;
//import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.service.IMatchService;
import com.org.cricketleagueapp.service.ITeamService;
import com.org.cricketleagueapp.service.ITournamentService;
//import com.org.cricketleagueapp.service.MatchServiceImpl;

@RestController
@RequestMapping("/match")
public class MatchController {
	
	@Autowired
	private IMatchService matchService;
	
	@Autowired
	private ITeamService teamService;
	
	@Autowired
	private ITournamentService tournamentService;
	

	@RequestMapping("/insert")
	@PostMapping
	public ResponseEntity<Match> insertMatch(@RequestBody MatchDummy match){
		Match matchTemp = new Match();
		matchTemp.setTeam1(teamService.getTeam(match.getTeam1_id()));
		matchTemp.setTeam2(teamService.getTeam(match.getTeam2_id()));
		matchTemp.setTournament(tournamentService.getTournament(match.getTournament_id()));
		return new ResponseEntity<Match>(matchService.insertMatch(matchTemp),HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Match> getMatch(@PathVariable long id){
		return new ResponseEntity<Match>(matchService.getMatch(id),HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Match> updateMatch(@RequestBody Match match, @PathVariable long id){
		return new ResponseEntity<Match>(matchService.updateMatch(match,id),HttpStatus.OK);
	}
	
	@GetMapping("/team1/{id}")
	public ResponseEntity<Team> getTeam1(@PathVariable long id){
		return new ResponseEntity<Team>(matchService.getTeam1(id),HttpStatus.OK);
	}
	
	@GetMapping("/team2/{id}")
	public ResponseEntity<Team> getTeam2(@PathVariable long id){
		return new ResponseEntity<Team>(matchService.getTeam2(id),HttpStatus.OK);
	}
	
	@GetMapping("/teams/{id}")
	public Map<String, Team> getTeams(@PathVariable long id){
		return matchService.getTeams(id);
	}
	
//	@GetMapping("/tournament/{id}")
//	public ResponseEntity<Tournament> getTournament(@PathVariable long id){
//		return new ResponseEntity<Tournament>(matchService.getTournament(id),HttpStatus.OK);
//	}
	
	@GetMapping("/audience/{id}")
	public Set<Audience> getAllAudience(@PathVariable long id){
		return matchService.getAllAudience(id);
	}
	
	@GetMapping("/schedule/{id}")
	public ResponseEntity<Schedule> getSchedule(@PathVariable long id){
		return new ResponseEntity<Schedule>(matchService.getSchedule(id),HttpStatus.OK);
	}
	
	@GetMapping("/audiences/{id}")
	public ResponseEntity<Audience> getAudience(@PathVariable int id){
		return new ResponseEntity<Audience>(matchService.getAudience(id),HttpStatus.OK);
	}
}
