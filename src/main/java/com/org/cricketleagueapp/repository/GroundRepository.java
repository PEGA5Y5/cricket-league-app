package com.org.cricketleagueapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Ground;


public interface GroundRepository extends JpaRepository<Ground, Integer> {

}
