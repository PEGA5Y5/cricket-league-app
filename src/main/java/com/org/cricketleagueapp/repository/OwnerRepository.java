package com.org.cricketleagueapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.cricketleagueapp.entity.Owner;



public interface OwnerRepository extends JpaRepository<Owner, Integer> {

}
