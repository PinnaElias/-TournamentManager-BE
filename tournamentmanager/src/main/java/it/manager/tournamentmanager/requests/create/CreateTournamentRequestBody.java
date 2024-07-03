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
    private Game game;
    @NotBlank(message = "name cannot be empty.")
    private String name;
    private List<Team> participants;
    private Bracket bracket;
    private MatchState tournamentState;
    private User tournamentManager;
    @NotBlank(message = "Please provide a brief description")
    private String description;
    private String prize;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private LocalTime startingTime;
}
