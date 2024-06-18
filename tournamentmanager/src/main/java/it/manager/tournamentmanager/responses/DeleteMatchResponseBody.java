package it.manager.tournamentmanager.responses;

import it.manager.tournamentmanager.entities.Match;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteMatchResponseBody {
    private String message;
    private Match match;
}
