package com.example.nba.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Standing")
@Getter
@Setter

public class Standing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer Win;
    private Integer Lost;
    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;
    @ManyToOne
    @JoinColumn(name="season_id")
    private Season season;
}
