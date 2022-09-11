package com.org.cricketleagueapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Ground;
import com.org.cricketleagueapp.entity.Match;


public interface GroundRepository extends JpaRepository<Ground, Integer> {

}
