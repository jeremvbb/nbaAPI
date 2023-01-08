package com.example.nba.controller;

import com.example.nba.model.Game;
import com.example.nba.model.Season;
import com.example.nba.model.Standing;
import com.example.nba.repository.SeasonRepository;
import com.example.nba.service.SeasonService;
import com.example.nba.service.StandingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/standing")
@AllArgsConstructor
public class StandingController {
    @Autowired
    private SeasonRepository seasonRepository;
    private final StandingService standingService;

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Standing create(@RequestBody Standing standing){
        return standingService.creer(standing);
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
   //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/read")
    public List<Standing> read(){

        return standingService.lire();
    }



    @PutMapping("/update/{id}")
    public Standing update(@PathVariable Long id, @RequestBody Standing standing){
        return standingService.modifier(id, standing);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return standingService.supprimer(id);
    }


}
