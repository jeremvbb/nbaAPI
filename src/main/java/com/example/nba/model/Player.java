package com.example.nba.model;



import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Player")
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 26)
    private String player_name;
    @Column(length = 26)
    private String player_firstname;
    private Integer age;
    private Double height;
    private Double weight;
    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team_player;


    private Date birthdate;
}
