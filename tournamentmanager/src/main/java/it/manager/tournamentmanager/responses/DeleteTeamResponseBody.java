package it.manager.tournamentmanager.responses;

import it.manager.tournamentmanager.entities.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTeamResponseBody {
    private String message;
    private Team team;
}
