package it.manager.tournamentmanager.requests.update;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.enums.Role;
import it.manager.tournamentmanager.entities.enums.UserRole;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUserRequestBody {

    private String password;

    private String username;

    private String firstName;

    private String lastName;

    @Email(message = "email does not have the right format")
    private String email;

    private List<Game> likedGames;

    private Role preferredRole;

    private String nationality;

    private String avatarUrl;

    private UserRole userRole;
}
