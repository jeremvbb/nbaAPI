package com.example.nba.service;

import com.example.nba.model.PlayerStatistic;
import com.example.nba.model.Season;

import java.util.List;

public interface PlayerStatisticService {
    PlayerStatistic creer(PlayerStatistic statistic);
    PlayerStatistic modifier(Long id,PlayerStatistic statistic);
    List<PlayerStatistic> lire();
    String supprimer(Long id);
}
