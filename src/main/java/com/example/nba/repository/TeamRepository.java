package com.example.nba.repository;

import com.example.nba.model.Game;
import com.example.nba.model.Player;
import com.example.nba.model.Standing;
import com.example.nba.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team,Long> {
    @Query("SELECT u FROM Player u WHERE u.team_player.id= ?1")
    List<Player> getReferredPlayers(Long id);
    @Query("SELECT u FROM Game u WHERE u.team1.id= ?1 OR u.team2.id=?1 and u.season.id=?2")
    List<Game> getReferredGames(Long id, Long seasonid);

    @Query("SELECT u FROM Standing u WHERE u.team.id= ?1 and u.season.id=?2")
    Standing getReferredStanding(Long id, Long id_season);
}
