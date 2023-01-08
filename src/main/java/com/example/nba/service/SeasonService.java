package com.example.nba.service;

import com.example.nba.model.Season;
import com.example.nba.model.Team;

import java.util.List;

public interface SeasonService {
    Season creer(Season season);
    Season modifier(Long id,Season season);
    List<Season> lire();
    String supprimer(Long id);
}
