package com.example.nba.service;

import com.example.nba.model.Player;
import com.example.nba.model.Team;

import java.util.List;

public interface TeamService {
    Team creer(Team player);
    Team modifier(Long id,Team player);
    List<Team> lire();
    String supprimer(Long id);
}
