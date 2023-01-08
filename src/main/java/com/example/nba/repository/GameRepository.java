package com.example.nba.repository;

import com.example.nba.model.Game;
import com.example.nba.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
