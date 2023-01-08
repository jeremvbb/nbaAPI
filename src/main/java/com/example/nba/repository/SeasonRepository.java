package com.example.nba.repository;

import com.example.nba.model.Game;
import com.example.nba.model.PlayerStatistic;
import com.example.nba.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeasonRepository extends JpaRepository<Season,Long> {
    @Query("SELECT u FROM Game u WHERE u.season.id= ?1")
    List<Game> getReferredGamesofSeason(Long id);
}
