package it.manager.tournamentmanager.responses;

import it.manager.tournamentmanager.entities.Game;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteGameResponseBody {
    private String message;
    private Game game;
}
