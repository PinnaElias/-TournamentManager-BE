package it.manager.tournamentmanager.requests.create;

import it.manager.tournamentmanager.entities.Bracket;
import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.User;
import it.manager.tournamentmanager.entities.enums.MatchState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CreateTournamentRequestBody {

    @URL
    private String avatar;

    @NotBlank(message = "Game must be set.")
    private Game game;

    @NotBlank(message = "name cannot be empty.")
    private String name;

    private List<Team> participants;

    @NotBlank(message = "Select a bracket type.")
    private Bracket bracket;

    @NotEmpty(message = "If it's a new tournament, select PLANNED.")
    private MatchState tournamentState;

    @NotEmpty(message = "Are you the tournament manager?")
    private User tournamentManager;

    @NotBlank(message = "Please provide a brief description")
    private String description;

    private String prize;

    @NotBlank(message = "Starting date must be set.")
    private LocalDate startingDate;

    private LocalDate endingDate;

    @NotBlank(message = "Starting time must be set.")
    private LocalTime startingTime;

}
