package it.manager.tournamentmanager.requests.update;

import it.manager.tournamentmanager.entities.Bracket;
import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.Tournament;
import it.manager.tournamentmanager.entities.enums.MatchState;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UpdateMatchRequestBody {
    private LocalDate startingDate;
    private LocalTime startingTime;
    private Team teamASide;
    private Team teamBSide;
    private int teamAScore;
    private int teamBScore;
    @Enumerated(EnumType.STRING)
    private MatchState matchState;
    private Tournament tournament;
    private Bracket bracket;
    private Team winner;
    private Team loser;
}
