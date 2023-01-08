package com.example.nba.service;

import com.example.nba.model.Game;
import com.example.nba.model.Player;

import java.util.List;

public interface GameService {
    Game creer( Game game);
    Game modifier(Long id, Game game);
    List< Game> lire();
    String supprimer(Long id);
}