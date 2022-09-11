package com.org.cricketleagueapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{

}
