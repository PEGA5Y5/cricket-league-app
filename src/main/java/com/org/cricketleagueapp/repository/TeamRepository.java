package com.org.cricketleagueapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Integer>{

}
