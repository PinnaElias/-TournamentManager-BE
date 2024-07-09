package it.manager.tournamentmanager.requests.update;

import it.manager.tournamentmanager.entities.Tournament;
import it.manager.tournamentmanager.entities.User;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import java.util.UUID;

@Data
public class UpdateTeamRequestBody {
    @URL
    private String avatar;
    private String name;
    private UUID game;
    private List<Tournament> activeTournaments;;
    private List<Tournament> tournamentsHistory;
    private List<User> members;
    private String nationality;
}
