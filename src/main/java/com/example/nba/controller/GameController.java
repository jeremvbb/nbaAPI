package com.example.nba.controller;

import com.example.nba.model.Game;
import com.example.nba.model.Team;
import com.example.nba.security.JwtAuthenticationFilter;
import com.example.nba.service.GameService;
import com.example.nba.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

   // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Game create(@RequestBody Game game){
        return gameService.creer(game);
    }
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/read")
    public List<Game> read(){

        return gameService.lire();
    }



    @PutMapping("/update/{id}")
    public Game update(@PathVariable Long id, @RequestBody Game game){
        return gameService.modifier(id, game);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return gameService.supprimer(id);
    }

}
