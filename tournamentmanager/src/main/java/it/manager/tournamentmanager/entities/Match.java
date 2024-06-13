package it.manager.tournamentmanager.entities;

import it.manager.tournamentmanager.entities.enums.MatchState;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class Match {

    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate startingDate;
    private LocalTime startingTime;
    private Team teamASide;
    private Team teamBSide;
    private int teamAScore;
    private int teamBScore;
    private MatchState matchState;
    private Bracket bracket;
    private Team winner;
    private Team loser;

}
