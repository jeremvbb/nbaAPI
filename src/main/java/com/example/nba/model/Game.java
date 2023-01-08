package com.example.nba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Game")
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team1;
    private Integer scoreTeam1;
    @ManyToOne
    @JoinColumn(name="team2_id")
    private Team team2;
    private Integer scoreTeam2;
    @ManyToOne
    @JoinColumn(name="season_id")
    private Season season;

}
