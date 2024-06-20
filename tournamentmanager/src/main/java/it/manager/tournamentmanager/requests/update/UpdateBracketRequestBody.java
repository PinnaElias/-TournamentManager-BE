package it.manager.tournamentmanager.requests.update;

import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.Tournament;
import it.manager.tournamentmanager.entities.enums.BracketType;
import lombok.Data;

import java.util.List;

@Data
public class UpdateBracketRequestBody {
    private BracketType bracketType;
    private Tournament tournament;
    private List<Team> participants;
    private Team winner;
    private List<Team> losers;
}
