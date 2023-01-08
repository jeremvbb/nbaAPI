package com.example.nba.service;

import com.example.nba.model.Player;

import java.util.List;

public interface PlayerService {
    Player creer(Player player);
    Player modifier(Long id,Player player);
    List<Player> lire();
    String supprimer(Long id);
}
