package it.manager.tournamentmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.manager.tournamentmanager.entities.enums.MatchState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Tournament {
    @Id
    @GeneratedValue
    private UUID id;
    private String avatar;
    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnoreProperties("tournaments")
    private Game game;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "tournament_participants",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    @JsonIgnoreProperties("tournaments")
    private List<Team> participants;
    @OneToOne
    @JoinColumn(name = "bracket_id")
    @JsonIgnoreProperties("tournaments")
    private Bracket bracket;
    @Enumerated(EnumType.STRING)
    private MatchState tournamentState;
    @OneToOne
    @JoinColumn(name = "manager_id")
    private User tournamentManager;
    private String description;
    private String prize;
    @Column(name = "starting_date")
    private LocalDate startingDate;
    @Column(name = "ending_date")
    private LocalDate endingDate;
    @Column(name = "starting_time")
    private LocalTime startingTime;
    @OneToOne
    @JoinColumn(name = "winner_id")
    private Team winner;
    @ManyToMany
    @JoinTable(
            name = "tournament_losers",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    @JsonIgnoreProperties("tournaments")
    private List<Team> losers;
}
