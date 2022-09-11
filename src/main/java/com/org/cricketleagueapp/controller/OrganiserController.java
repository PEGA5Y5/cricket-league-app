package com.org.cricketleagueapp.controller;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.org.cricketleagueapp.entity.TournamentSerialized;
import com.org.cricketleagueapp.service.IOrganiserService;
import com.org.cricketleagueapp.service.ITournamentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/organiser")
public class OrganiserController {
	
	@Autowired
	private IOrganiserService orgnaniserService;
	
	@Autowired
	private ITournamentService tournamentService;
	
	@PostMapping("/add-tournament")
	public ResponseEntity<Tournament> insertTournament(@RequestBody Tournament tournament) {
		return new ResponseEntity<Tournament>(tournamentService.insertTournament(tournament),HttpStatus.CREATED);
	}
	
	@PostMapping("/add-organiser")
	public ResponseEntity<Organiser> insertOrganiser(@RequestBody Organiser organiser) {
		return new ResponseEntity<Organiser>(orgnaniserService.insertOrganiser(organiser),HttpStatus.CREATED);
	}
	
	@GetMapping("/get-organiser-by-id/{organiser-id}")
	public ResponseEntity<Organiser> getOrganiserById(@PathVariable("organiser-id") int organiserId) {
		return new ResponseEntity<Organiser>(orgnaniserService.getOrganiser(organiserId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-all-organisers")
	public  ResponseEntity<List<Organiser>> getAllOrganisers(){
		return new ResponseEntity<List<Organiser>>(orgnaniserService.getAllOrganisers(),HttpStatus.FOUND);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Organiser> updateOrganiser(@RequestBody Organiser organiser){
		return new ResponseEntity<Organiser>(orgnaniserService.updateOrganiser(organiser),HttpStatus.OK);
	}
	
	@GetMapping("/get-tournaments/{organiser-id}")
	public  ResponseEntity<List<Tournament>> getTournaments(@PathVariable("organiser-id") int organiserId){
		return new ResponseEntity<List<Tournament>>(orgnaniserService.getTournaments(organiserId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-tournament-by-id/{tournament-id}")
	public ResponseEntity<Tournament> getTournament(@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<Tournament>(orgnaniserService.getTournament(tournamentId),HttpStatus.FOUND);
	}
	
	@GetMapping("/get-budget/{organiser-id}")
	public ResponseEntity<Double> getBudget(@PathVariable("organiser-id") int organiserId) {
		return new ResponseEntity<Double>(orgnaniserService.getBudget(organiserId),HttpStatus.FOUND);
	}
	
	@PutMapping("/set-tournament-date-time/{tournament-id}")
	public ResponseEntity<Tournament> setTournamentDateTime(@RequestBody TournamentSerialized dateTime,@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<Tournament>(orgnaniserService.setTournamentDateTime(tournamentId, LocalDate.parse(dateTime.getStartDate()), LocalTime.parse(dateTime.getStartTime()), LocalTime.parse(dateTime.getEndTime())),HttpStatus.OK);
	}
	
	@PutMapping("/set-tournament-ground/{tournament-id}/{ground-id}")
	public  ResponseEntity<Tournament> setTournamentGround(@PathVariable("tournament-id") int tournamentId, @PathVariable("team-id") int groundId) {
		return new ResponseEntity<Tournament>(orgnaniserService.setTournamentGround(tournamentId, groundId),HttpStatus.OK);
	}
	
	@PutMapping("/set-tournament-prize-money/{tournament-id}/{prize-money}")
	public  ResponseEntity<Tournament> setTournamentPrizeMoney(@PathVariable("tournament-id") int tournamentId, @PathVariable("prize-money") double prizeMoney) {
		return new ResponseEntity<Tournament>(orgnaniserService.setTournamentPrizeMoney(tournamentId, prizeMoney),HttpStatus.OK);
	}
	
	@PutMapping("/add-team-to-tournament/{tournament-id}/{team-id}")
	public  ResponseEntity<Team> addTeamToTournament(@PathVariable("tournament-id") int tournamentId, @PathVariable("team-id") int teamId) {
		return new ResponseEntity<Team>(tournamentService.addTeamToTournament(tournamentId, teamId),HttpStatus.OK);
	}
	
	@GetMapping("/get-all-teams/{tournament-id}")
	public  ResponseEntity<List<Team>> getTeams(@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<List<Team>>(tournamentService.getTeams(tournamentId),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/delete-team-from-torunament/{tournament-id}/{team-id}")
	public ResponseEntity<Team> deleteTeamFromTournament(@PathVariable("tournament-id") int tournamentId,  @PathVariable("team-id") int teamId) {
		return new ResponseEntity<Team>(tournamentService.deleteTeamFromTournament(tournamentId, teamId),HttpStatus.OK);
	}
	
	@GetMapping("/get-all-matches/{tournament-id}")
	public  ResponseEntity<List<Match>> getAllMatches(@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<List<Match>>(tournamentService.getAllMatches(tournamentId),HttpStatus.FOUND);
	}
	
	@PutMapping("/start-tournament/{tournament-id}")
	public  ResponseEntity<Tournament> startTournament(@PathVariable("tournament-id") int tournamentId) {
		return new ResponseEntity<Tournament>(tournamentService.startTournament(tournamentId),HttpStatus.OK);
	}
	
	@PutMapping("/end-tournament/{tournament-id}/{owner-id}")
	public  ResponseEntity<Tournament> endTournament(@PathVariable("tournament-id") int tournamentId, @PathVariable("owner-id") int ownerId) {
		return new ResponseEntity<Tournament>(tournamentService.endTournament(tournamentId, ownerId),HttpStatus.OK);
	}
}

