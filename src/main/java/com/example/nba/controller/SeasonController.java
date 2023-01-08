package com.example.nba.controller;

import com.example.nba.model.Game;
import com.example.nba.model.Season;
import com.example.nba.model.Team;
import com.example.nba.repository.SeasonRepository;
import com.example.nba.security.JwtAuthenticationFilter;
import com.example.nba.service.SeasonService;
import com.example.nba.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/season")
@AllArgsConstructor
public class SeasonController {
    @Autowired
    private SeasonRepository seasonRepository;
    private final SeasonService teamService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Season create(@RequestBody Season player){
        return teamService.creer(player);
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")

    @GetMapping("/read")
    public List<Season> read(){

        return teamService.lire();
    }



    @PutMapping("/update/{id}")
    public Season update(@PathVariable Long id, @RequestBody Season season){
        return teamService.modifier(id, season);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return teamService.supprimer(id);
    }

    @GetMapping("/read/get-games-season")
    public List<Game> getSeasonGame(Long Id){


        return  seasonRepository.getReferredGamesofSeason(Id);
    }

}
