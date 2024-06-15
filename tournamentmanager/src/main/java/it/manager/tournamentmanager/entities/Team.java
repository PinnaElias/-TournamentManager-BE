package it.manager.tournamentmanager.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Team {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String avatar;
    private Game game;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToMany
    @JoinTable(
            name = "tournament_participants",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> activeTournaments;

    @ManyToMany
    @JoinTable(
            name = "tournament_history",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> tournamentsHistory;

    @OneToMany(mappedBy = "teams")
    private List<User> members;
    private String nationality;
}
