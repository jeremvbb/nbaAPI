package com.example.nba.repository;

import com.example.nba.model.Player;
import com.example.nba.model.PlayerStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerStatisticRepository extends JpaRepository<PlayerStatistic,Long> {

}
