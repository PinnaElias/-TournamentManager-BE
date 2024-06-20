package it.manager.tournamentmanager.requests.update;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Tournament;
import it.manager.tournamentmanager.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class UpdateTeamRequestBody {

    @URL
    private String avatar;
    private String name;
    private Game game;
    private List<Tournament> activeTournaments;;
    private List<Tournament> tournamentsHistory;
    private List<User> members;
    private String nationality;
}
