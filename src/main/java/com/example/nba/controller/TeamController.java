package com.example.nba.controller;

import com.example.nba.model.Game;
import com.example.nba.model.Player;
import com.example.nba.model.Standing;
import com.example.nba.model.Team;
import com.example.nba.repository.TeamRepository;
import com.example.nba.security.JwtAuthenticationFilter;
import com.example.nba.service.PlayerService;
import com.example.nba.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/team")
@AllArgsConstructor
public class TeamController {

        @Autowired
        private TeamRepository teamRepository;
        private final TeamService teamService;
        private JwtAuthenticationFilter jwtAuthenticationFilter;
        @PreAuthorize("hasRole('ADMIN')")
        @PostMapping("/create")
        public Team create(@RequestBody Team player){
            return teamService.creer(player);
        }
        //@PreAuthorize("hasRole('ROLE_ADMIN')")
        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/read")
        public List<Team> read(){

            return teamService.lire();
        }



        @PutMapping("/update/{id}")
        public Team update(@PathVariable Long id, @RequestBody Team player){
            return teamService.modifier(id, player);
        }

        @DeleteMapping("/delete/{id}")
        public String delete(@PathVariable Long id){
            return teamService.supprimer(id);
        }
        @GetMapping("/read/get-games-season")
        public List<Game> getSeasonGame(Long Id, Long season){


                return  teamRepository.getReferredGames(Id, season);
        }
        @GetMapping("/read/get-standing-season")
        public Standing getSeasonStanding(Long Id, Long IdSeason){


                return  teamRepository.getReferredStanding(Id,IdSeason);
        }


}
