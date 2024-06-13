package it.manager.tournamentmanager.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Team {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String avatar;
    private Game game;

    @OneToMany(mappedBy = "team")
    private List<Tournament> activeTournaments;
    @OneToMany(mappedBy = "team")
    private List<Tournament> tournamentsHistory;

    @OneToMany(mappedBy = "team")
    private List<User> members;
    private String nationality;
}
