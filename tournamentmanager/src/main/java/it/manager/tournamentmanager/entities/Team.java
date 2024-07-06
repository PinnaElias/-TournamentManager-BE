package it.manager.tournamentmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToMany
    @JoinTable(
            name = "team_active_tournaments",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> activeTournaments;
    @ManyToMany
    @JoinTable(
            name = "team_tournaments_history",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> tournamentsHistory;
    @OneToMany(mappedBy = "team")
    @JsonIgnoreProperties("team")
    private List<User> members;
    private String nationality;
}
