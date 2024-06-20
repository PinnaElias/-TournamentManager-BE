package it.manager.tournamentmanager.responses;

import it.manager.tournamentmanager.entities.Tournament;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTournamentResponseBody {
    private String message;
    private Tournament tournament;
}
