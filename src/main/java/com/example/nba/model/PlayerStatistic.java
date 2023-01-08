package com.example.nba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PlayerStatistics")
@Getter
@Setter

public class PlayerStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Double points;
    private Double rebounds;
    private Double assists;
    private Integer foul;
    private Double minutespergame;
    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;
    @ManyToOne
    @JoinColumn(name="season_id")
    private Season season;
}
