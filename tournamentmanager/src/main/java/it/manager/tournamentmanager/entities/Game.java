package it.manager.tournamentmanager.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.UUID;

@Data
public class Game {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private int maxPlayersNumberForTeam;
    private int activeTournaments;
    private String avatar;
}
