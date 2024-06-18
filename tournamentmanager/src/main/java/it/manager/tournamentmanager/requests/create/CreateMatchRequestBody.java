package it.manager.tournamentmanager.requests.create;

import it.manager.tournamentmanager.entities.Bracket;
import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.Tournament;
import it.manager.tournamentmanager.entities.enums.MatchState;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateMatchRequestBody {

    @FutureOrPresent
    private LocalDate startingDate;

    private LocalTime startingTime;

    @NotBlank(message = "Specify the Challenger!")
    private Team teamASide;

    @NotBlank(message = "Specify the Challenged!")
    private Team teamBSide;;

    @Enumerated(EnumType.STRING)
    private MatchState matchState;

    @NotBlank(message = "Specify the tournament!")
    private Tournament tournament;

    @NotBlank(message = "Specify the Bracket type!")
    private Bracket bracket;
}
