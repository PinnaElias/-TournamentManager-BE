package it.manager.tournamentmanager.requests.create;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Tournament;
import it.manager.tournamentmanager.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class CreateTeamRequestBody {

    @NotBlank(message = "Choose a name!")
    private String name;
    @URL
    private String avatar;
    @NotBlank(message = "Choose a game!")
    private Game game;
    @NotBlank(message = "Add at least yourself to your team!")
    private List<User> members;
    @NotBlank(message = "Where are you from? :)")
    private String nationality;
}
