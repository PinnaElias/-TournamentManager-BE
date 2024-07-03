package it.manager.tournamentmanager.requests.update;

import it.manager.tournamentmanager.entities.Bracket;
import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.enums.MatchState;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateTournamentRequestBody {
    @URL
    private String avatar;
    private Game game;
    private String name;
    private List<Team> participants;
    private Bracket bracket;
    private MatchState tournamentState;
    private String description;
    private String prize;
    private LocalDate endingDate;
    private Team winner;
    private List<Team> losers;
}
