package it.manager.tournamentmanager.requests.update;

import it.manager.tournamentmanager.entities.User;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class UpdateGameRequestBody {
    private String name;
    private int maxPlayersNumberForTeam;
    @URL(message = "URL does not have the right format")
    private String avatar;
    private List<User> subscribers;
}
