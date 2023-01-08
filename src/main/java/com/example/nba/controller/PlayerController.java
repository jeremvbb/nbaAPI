package com.example.nba.controller;

import com.example.nba.model.Game;
import com.example.nba.model.Player;
import com.example.nba.model.PlayerStatistic;
import com.example.nba.repository.PlayerRepository;
import com.example.nba.repository.TeamRepository;
import com.example.nba.security.JwtAuthenticationFilter;
import com.example.nba.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/player")
@AllArgsConstructor

public class PlayerController {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    private final PlayerService playerService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Player create(@RequestBody Player player){
        return playerService.creer(player);
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/read")
    public List<Player> read(){

        return playerService.lire();
    }



    @PutMapping("/update/{id}")
    public Player update(@PathVariable Long id, @RequestBody Player player){
        return playerService.modifier(id, player);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return playerService.supprimer(id);
    }
    @GetMapping("/read/team-players")
    public List<Player> getRefferedPlayers(Long Id){


        return  teamRepository.getReferredPlayers(Id);
    }
    @GetMapping("/read/stats-players")
    public List<PlayerStatistic> getStats(Long Id){


        return  playerRepository.getReferredStats(Id);
    }

}
