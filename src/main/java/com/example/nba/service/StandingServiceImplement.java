package com.example.nba.service;

import com.example.nba.model.Season;
import com.example.nba.model.Standing;
import com.example.nba.repository.SeasonRepository;
import com.example.nba.repository.StandingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StandingServiceImplement implements StandingService{
    private StandingRepository repository;
    @Override
    public Standing creer(Standing season) {
        return repository.save(season);
    }

    @Override
    public Standing modifier(Long id, Standing season) {
        return repository.findById(id)
                .map( p ->{
                    p.setWin(season.getWin());
                    p.setLost(season.getLost());
                    p.setTeam(season.getTeam());
                    p.setSeason(season.getSeason());

                    return repository.save(p);
                }).orElseThrow(() ->new RuntimeException("Saison modifié"));
    }

    @Override
    public List<Standing> lire() {
        return repository.findAll();
    }

    @Override
    public String supprimer(Long id) {
        repository.deleteById(id);
        return "saison supprimé";
    }
}
