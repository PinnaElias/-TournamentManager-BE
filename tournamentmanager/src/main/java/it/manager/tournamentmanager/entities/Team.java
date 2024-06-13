package it.manager.tournamentmanager.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private List<Tournament> activeTournaments;
    private List<Tournament> tournamentsHistory;
    private List<User> members;
    private String nationality;
}
