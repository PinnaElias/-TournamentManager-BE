package it.manager.tournamentmanager.requests.create;

import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.Tournament;
import it.manager.tournamentmanager.entities.enums.BracketType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CreateBracketRequestBody {
    @NotBlank(message = "Define the bracket type!")
    private BracketType bracketType;
    private Tournament tournament;
    private List<Team> participants;
}
