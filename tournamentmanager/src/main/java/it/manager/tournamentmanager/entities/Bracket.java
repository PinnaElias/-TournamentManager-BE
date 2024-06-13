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
    private BracketType bracketType;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToMany
    @JoinTable(
            name = "bracket_team",
            joinColumns = @JoinColumn(name = "bracket_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> participants;

    @OneToMany(mappedBy = "bracket")
    private List<Match> matches;
    private Team winner;
    private List<Team> losers;
}
