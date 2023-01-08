package com.example.nba.service;

import com.example.nba.model.Player;
import com.example.nba.model.Team;
import com.example.nba.repository.PlayerRepository;
import com.example.nba.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TeamServiceImplement implements TeamService {
    private TeamRepository repository;
    @Override
    public Team creer(Team player) {
        return repository.save(player);
    }

    @Override
    public Team modifier(Long id, Team team) {
        return repository.findById(id)
                .map( p ->{
                    p.setTeam_name(team.getTeam_name());

                    return repository.save(p);
                }).orElseThrow(() ->new RuntimeException("Joueur modifié"));

    }

    @Override
    public List<Team> lire() {
        return repository.findAll();
    }

    @Override
    public String supprimer(Long id) {
        repository.deleteById(id);
        return "joueur supprimé";
    }
}
