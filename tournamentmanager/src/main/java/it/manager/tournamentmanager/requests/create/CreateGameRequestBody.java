package it.manager.tournamentmanager.requests.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateGameRequestBody {
    @NotBlank(message = "It must have a name!")
    private String name;
    @NotBlank(message = "Select the max number of members a given team can have")
    private int maxPlayersNumberForTeam;
    private String avatar;
}
