package com.example.nba.repository;

import com.example.nba.model.Game;
import com.example.nba.model.Player;
import com.example.nba.model.PlayerStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {

    @Query("SELECT u FROM PlayerStatistic u WHERE u.player.id= ?1")
    List<PlayerStatistic> getReferredStats(Long id);

}
