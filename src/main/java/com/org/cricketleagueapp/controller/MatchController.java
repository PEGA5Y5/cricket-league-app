package com.org.cricketleagueapp.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cricketleagueapp.entity.Audience;
import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.MatchSerialized;
import com.org.cricketleagueapp.entity.Schedule;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.exception.GroundNotFoundException;
import com.org.cricketleagueapp.service.IGroundService;
import com.org.cricketleagueapp.service.IMatchService;
import com.org.cricketleagueapp.service.ITeamService;
import com.org.cricketleagueapp.service.ITournamentService;

@RestController
@RequestMapping("/match")
public class MatchController {
	
	@Autowired
	private IMatchService matchService;
	
	@Autowired
	private ITeamService teamService;
	
	@Autowired
	private ITournamentService tournamentService;
	
	@Autowired
	private IGroundService groundService;
	

	@RequestMapping("/insert/{groundId}")
	@PostMapping
	public ResponseEntity<Match> insertMatch(@RequestBody Match match, @PathVariable int groundId,@PathVariable int tournamentId){
		/*Match matchTemp = new Match();
		matchTemp.setTeam1(teamService.getTeam(match.getTeam1_id()));
		matchTemp.setTeam2(teamService.getTeam(match.getTeam2_id()));
		matchTemp.setTournament(tournamentService.getTournament(match.getTournament_id()));
		matchTemp.setGround(groundService.getGround(match.getGround_id()));*/
		int team1_id = match.getTeam1().getTeamId();
		int team2_id = match.getTeam2().getTeamId();
		Tournament tournament = tournamentService.getTournament(tournamentId);
		Team team1 = teamService.getTeam(team1_id);
		Team team2 = teamService.getTeam(team2_id);
		match.setTournament(tournament);
		match.setTeam1(team1);
		match.setTeam2(team2);
		Ground ground = groundService.getGround(groundId);
		if(ground == null)
			throw new GroundNotFoundException("This Ground does not exist");
		match.setGround(ground);
		match.setAvailableSeats(ground.getCapacity());
		return new ResponseEntity<Match>(matchService.insertMatch(match),HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Match> getMatch(@PathVariable long id){
		return new ResponseEntity<Match>(matchService.getMatch(id),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Match> updateMatch(@RequestBody Match match){
		return new ResponseEntity<Match>(matchService.updateMatch(match),HttpStatus.OK);
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
	
	@GetMapping("/tournament/{id}")
	public ResponseEntity<Tournament> getTournament(@PathVariable long id){
		return new ResponseEntity<Tournament>(matchService.getTournament(id),HttpStatus.OK);
	}
	
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
	
	@DeleteMapping("/deleteMatch/{id}")
	public ResponseEntity<Match> deleteMatchById(@PathVariable long id){
		return new ResponseEntity<Match>(matchService.deleteMatchById(id),HttpStatus.OK);
	}
	
	@PutMapping("/schedule/update")
	public ResponseEntity<Match> updatSchedule(@RequestBody MatchSerialized match){
		Match saveMatch = new Match();
		saveMatch.setMatchId(match.getMatch_id());
		Schedule schedule = new Schedule();
		schedule.setMatchDate(LocalDate.parse(match.getMatchDate()));
		schedule.setStartTime(LocalTime.parse(match.getStartTime()));
		schedule.setEndTime(LocalTime.parse(match.getEndTime()));
		saveMatch.setSchedule(schedule);
		return new ResponseEntity<Match>(matchService.updateMatch(saveMatch),HttpStatus.OK);
	}
	
	@GetMapping("/getground/{matchId}")
	public ResponseEntity<Ground> getGround(@PathVariable long matchId){
		Match match = matchService.getMatch(matchId);
		return new ResponseEntity<Ground>(match.getGround(), HttpStatus.FOUND);
	}
}
