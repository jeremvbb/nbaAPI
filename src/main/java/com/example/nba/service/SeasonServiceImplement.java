package com.example.nba.service;

import com.example.nba.model.Season;
import com.example.nba.model.Team;
import com.example.nba.repository.PlayerRepository;
import com.example.nba.repository.SeasonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SeasonServiceImplement implements SeasonService{
    private SeasonRepository repository;
    @Override
    public Season creer(Season season) {
     return repository.save(season);
    }

    @Override
    public Season modifier(Long id, Season season) {
        return repository.findById(id)
                .map( p ->{
                    p.setSeason_year(season.getSeason_year());

                    return repository.save(p);
                }).orElseThrow(() ->new RuntimeException("Saison modifié"));
    }

    @Override
    public List<Season> lire() {
        return repository.findAll();
    }

    @Override
    public String supprimer(Long id) {
        repository.deleteById(id);
        return "saison supprimé";
    }
}
