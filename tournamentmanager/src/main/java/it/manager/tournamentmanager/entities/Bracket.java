package it.manager.tournamentmanager.entities;

import it.manager.tournamentmanager.entities.enums.BracketType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Bracket {

    @Id
    @GeneratedValue
    private UUID id;
    private BracketType bracketType;

    //    @ManyToOne
    private Tournament tournament;

    //    @ManyToOne
    private List<Team> participants;

    //      @OneToMany
    private List<Match> matches;
    private Team winner;
    private List<Team> losers;
}
