package it.manager.tournamentmanager.entities;

import it.manager.tournamentmanager.entities.enums.MatchState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
public class Match {

    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate startingDate;
    private LocalTime startingTime;

    @OneToOne
    @JoinColumn(name = "team_A_id")
    private Team teamASide;

    @OneToOne
    @JoinColumn(name = "team_B_id")
    private Team teamBSide;

    private int teamAScore;
    private int teamBScore;
    private MatchState matchState;

    @OneToOne
    @JoinColumn(name = "bracket_id")
    private Bracket bracket;

    @OneToOne
    @JoinColumn(name = "winner_id")
    private Team winner;

    @OneToOne
    @JoinColumn(name = "loser_id")
    private Team loser;

}
