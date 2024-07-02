package it.manager.tournamentmanager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Game {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private int maxPlayersNumberForTeam;
    private String avatar;

    @ManyToMany
    @JoinTable(
            name = "game_subscribers",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("subscribedGames")
    private List<User> subscribers;

    @OneToMany(mappedBy = "game")
    @JsonIgnoreProperties("game")  // Ignora la proprietà ricorsiva
    private List<Tournament> tournaments;
}
