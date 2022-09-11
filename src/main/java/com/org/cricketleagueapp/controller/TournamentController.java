package com.org.cricketleagueapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Organiser;
import com.org.cricketleagueapp.entity.Team;
import com.org.cricketleagueapp.entity.Tournament;
import com.org.cricketleagueapp.service.IOrganiserService;
import com.org.cricketleagueapp.service.ITournamentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/tournament")					
public class TournamentController {
	
	@Autowired
	private ITournamentService tournamentService;
	
	@Autowired
	private IOrganiserService organiserService;
	
	@PostMapping("/add-tournament/{organiserId}")
	public ResponseEntity<Tournament> addTournament(@RequestBody Tournament tournament,@PathVariable int organiserId) {
		Organiser organiser = organiserService.getOrganiser(organiserId);
		tournament.setOrganiser(organiser);
		return new ResponseEntity<Tournament>(tournamentService.insertTournament(tournament),HttpStatus.CREATED);
	}
	
	@PostMapping("/add-tournament")
	public ResponseEntity<Tournament> insertTournament(@RequestBody Tournament tournament) {
		return new ResponseEntity<Tournament>(tournamentService.insertTournament(tournament),HttpStatus.CREATED);
	}
	
	@GetMapping("/get-tournament-by-id/{tournament-id}")
	public ResponseEntity<Tournament> getTournamentById(@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<Tournament>(tournamentService.getTournament(tournamentId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-all-tournaments")
	public  ResponseEntity<List<Tournament>> getAllTournaments() {
		return new ResponseEntity<List<Tournament>>(tournamentService.getAllTournaments(),HttpStatus.FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Tournament> updateTournament(@RequestBody Tournament tournament) {
		return new ResponseEntity<Tournament>(tournamentService.updateTournament(tournament),HttpStatus.OK);
	}

	@DeleteMapping("/delete/{tournament-id}")
	public ResponseEntity<Tournament> deleteTournament(@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<Tournament>(tournamentService.deleteTournament(tournamentId),HttpStatus.OK);
	}
	
	@GetMapping("/get-all-matches/{tournament-id}")
	public  ResponseEntity<List<Match>> getAllMatches(@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<List<Match>>(tournamentService.getAllMatches(tournamentId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-match/{tournament-id}/{match-id}")
	public  ResponseEntity<Match> getMatch(@PathVariable("tournament-id") int tournamentId, @PathVariable("match-id") long matchId) {
		return new ResponseEntity<Match>(tournamentService.getMatch(tournamentId, matchId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-tournament")
	public  ResponseEntity<Tournament> getTournament(@RequestBody Match match) {
		return new ResponseEntity<Tournament>(tournamentService.getTournament(match),HttpStatus.FOUND);
	}
	
	@PutMapping("/add-team-to-tournament/{tournament-id}/{team-id}")
	public  ResponseEntity<Team> addTeamToTournament(@PathVariable("tournament-id") int tournamentId, @PathVariable("team-id") int teamId) {
		return new ResponseEntity<Team>(tournamentService.addTeamToTournament(tournamentId, teamId),HttpStatus.CREATED);
	}
	
	@GetMapping("/get-all-teams/{tournament-id}")
	public  ResponseEntity<List<Team>> getTeams(@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<List<Team>>(tournamentService.getTeams(tournamentId),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/delete-team-from-torunament/{tournament-id}/{team-id}")
	public ResponseEntity<Team> deleteTeamFromTournament(@PathVariable("tournament-id") int tournamentId,  @PathVariable("team-id") int teamId) {
		return new ResponseEntity<Team>(tournamentService.deleteTeamFromTournament(tournamentId, teamId),HttpStatus.OK);
	}
}


