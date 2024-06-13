package it.manager.tournamentmanager.entities;

import it.manager.tournamentmanager.entities.enums.BracketType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
public class Bracket {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private BracketType bracketType;

//-Id
//-Nome
//-Tipo (es. Single Elimination
//-Torneo (relazione many to one con TORNEO)
//-Team partecipanti (relazione one to many con TEAM)
//-Partite (relazione one to many con PARTITA)
//-Vincitore
//-Sconfitti
}
