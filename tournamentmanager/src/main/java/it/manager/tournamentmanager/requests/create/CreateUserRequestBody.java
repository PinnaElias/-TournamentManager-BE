package it.manager.tournamentmanager.requests.create;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequestBody {


    //COPIA DALLA CLASS USER
    @NotBlank(message = "password cannot be empty")
    private String password;

    @NotBlank(message = "nickname cannot be empty")
    private String nickname;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "surname cannot be empty")
    private String surname;
    @NotBlank(message = "e-mail cannot be empty")
    private String email;

    @NotBlank(message = "choose at least one option!")
    private List<Game> likedGames;

    @NotBlank(message = "choose at least one option!")
    private Role preferredRole;

    @NotBlank(message = "nationality cannot be empty")
    private String nationality;

    @NotBlank(message = "avatar url cannot be empty")
    private String avatarUrl;

    @NotBlank(message = "user must have a role")
    private String userRole;
}