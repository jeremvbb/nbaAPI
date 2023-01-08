package com.example.nba.service;

import com.example.nba.model.Season;
import com.example.nba.model.Standing;

import java.util.List;

public interface StandingService {
    Standing creer(Standing standing);
    Standing modifier(Long id,Standing standing);
    List<Standing> lire();
    String supprimer(Long id);
}
