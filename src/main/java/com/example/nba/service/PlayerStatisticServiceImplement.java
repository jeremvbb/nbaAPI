package com.example.nba.service;

import com.example.nba.model.PlayerStatistic;
import com.example.nba.model.Season;
import com.example.nba.repository.PlayerStatisticRepository;
import com.example.nba.repository.SeasonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerStatisticServiceImplement implements PlayerStatisticService{
    private PlayerStatisticRepository repository;

    @Override
    public PlayerStatistic creer(PlayerStatistic statistic) {
        return repository.save(statistic);
    }

    @Override
    public PlayerStatistic modifier(Long id, PlayerStatistic statistic) {
        return repository.findById(id)
                .map( p ->{
                    p.setPlayer(statistic.getPlayer());
                    p.setAssists(statistic.getAssists());
                    p.setPoints(statistic.getPoints());
                    p.setRebounds(statistic.getRebounds());
                    p.setSeason(statistic.getSeason());
                    p.setMinutespergame(statistic.getMinutespergame());
                    return repository.save(p);
                }).orElseThrow(() ->new RuntimeException("Statistiques modifié"));
    }

    @Override
    public List<PlayerStatistic> lire() {
        return repository.findAll();
    }

    @Override
    public String supprimer(Long id) {
        repository.deleteById(id);
        return "statistiques supprimé";
    }
}
