package com.example.nba.service;

import com.example.nba.model.Player;
import com.example.nba.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PlayerServiceImplement implements PlayerService{
   private PlayerRepository repository;
    @Override
    public Player creer(Player player) {
       return repository.save(player);
    }

    @Override
    public Player modifier(Long id, Player player) {
       return repository.findById(id)
               .map( p ->{
                   p.setPlayer_name(player.getPlayer_name());
                   p.setPlayer_firstname(player.getPlayer_firstname());
                   p.setAge(player.getAge());
                   p.setHeight(player.getHeight());
                   p.setWeight(player.getWeight());
                  p.setTeam_player(player.getTeam_player());
                   p.setBirthdate(player.getBirthdate());
                   return repository.save(p);
               }).orElseThrow(() ->new RuntimeException("Joueur modifié"));

    }

    @Override
    public List<Player> lire() {
        return repository.findAll();
    }

    @Override
    public String supprimer(Long id) {
         repository.deleteById(id);
        return "joueur supprimé";
    }
}
