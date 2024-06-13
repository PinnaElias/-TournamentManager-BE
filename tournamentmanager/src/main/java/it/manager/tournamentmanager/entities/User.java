package it.manager.tournamentmanager.entities;

import it.manager.tournamentmanager.entities.enums.Role;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String nickname;
    private String name;
    private String surname;
    private String avatar;

    private List<Game> likedGames;

    private List<Team> teams;

    private Role preferredRole;
    private String nationality;
    private int mvpCount;
}
