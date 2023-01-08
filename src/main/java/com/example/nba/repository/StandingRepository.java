package com.example.nba.repository;

import com.example.nba.model.Standing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StandingRepository extends JpaRepository<Standing,Long> {
}
