package com.example.nba.controller;

import com.example.nba.model.Player;
import com.example.nba.model.PlayerStatistic;
import com.example.nba.model.Season;
import com.example.nba.service.PlayerStatisticService;
import com.example.nba.service.SeasonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistic")
@AllArgsConstructor

public class PlayerStatisticController {
    private final PlayerStatisticService statService;

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public PlayerStatistic create(@RequestBody PlayerStatistic player){
        return statService.creer(player);
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/read")
    public List<PlayerStatistic> read(){

        return statService.lire();
    }



    @PutMapping("/update/{id}")
    public PlayerStatistic update(@PathVariable Long id, @RequestBody PlayerStatistic stat){
        return statService.modifier(id, stat);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return statService.supprimer(id);
    }

}
