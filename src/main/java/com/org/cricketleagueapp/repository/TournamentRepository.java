package com.org.cricketleagueapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Match;
import com.org.cricketleagueapp.entity.Tournament;



public interface TournamentRepository extends JpaRepository<Tournament, Integer> {

}
