package it.manager.tournamentmanager.requests.create;

import it.manager.tournamentmanager.entities.Bracket;
import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.User;
import it.manager.tournamentmanager.entities.enums.BracketType;
import it.manager.tournamentmanager.entities.enums.MatchState;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public record CreateTournamentRequestBody(
        @URL
        String avatar,
        Game game,
        @NotBlank(message = "name cannot be empty.")
        String name,
        List<UUID> participants,
//ritocca anche il service
        UUID bracket,
        MatchState tournamentState,
        UUID tournamentManager,
        @NotBlank(message = "Please provide a brief description")
        String description,
        String prize,
        LocalDate startingDate,
        LocalDate endingDate,
        LocalTime startingTime
) {
}
