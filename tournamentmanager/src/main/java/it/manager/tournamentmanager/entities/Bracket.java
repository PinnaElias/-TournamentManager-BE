package it.manager.tournamentmanager.entities;

import it.manager.tournamentmanager.entities.enums.BracketType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Bracket {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private BracketType bracketType;

    @OneToOne(mappedBy = "bracket")
    private Tournament tournament;

    @ManyToMany
    @JoinTable(
            name = "bracket_participants",
            joinColumns = @JoinColumn(name = "bracket_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> participants;

    @OneToOne
    @JoinColumn(name = "winner_id")
    private Team winner;

    @ManyToMany
    @JoinTable(
            name = "bracket_losers",
            joinColumns = @JoinColumn(name = "bracket_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> losers;
}
