package it.manager.tournamentmanager.responses;

import it.manager.tournamentmanager.entities.Bracket;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteBracketResponseBody {
    private String message;
    private Bracket bracket;
}
