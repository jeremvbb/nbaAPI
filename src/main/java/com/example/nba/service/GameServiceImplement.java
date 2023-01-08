package com.example.nba.service;

import com.example.nba.model.Game;
import com.example.nba.repository.GameRepository;
import com.example.nba.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class GameServiceImplement implements GameService {
    private GameRepository repository;
    @Override
    public Game creer(Game game) {
        return repository.save(game);
    }

    @Override
    public Game modifier(Long id, Game game) {
        return repository.findById(id)
                .map( p ->{
                    p.setTeam1(game.getTeam1());
                    p.setScoreTeam1(game.getScoreTeam1());
                    p.setTeam2(game.getTeam2());
                    p.setScoreTeam2(game.getScoreTeam2());
                    p.setSeason(game.getSeason());
                    return repository.save(p);
                }).orElseThrow(() ->new RuntimeException("Joueur modifié"));

    }

    @Override
    public List<Game> lire() {
        return repository.findAll();
    }

    @Override
    public String supprimer(Long id) {
        repository.deleteById(id);
        return "joueur supprimé";
    }
}
