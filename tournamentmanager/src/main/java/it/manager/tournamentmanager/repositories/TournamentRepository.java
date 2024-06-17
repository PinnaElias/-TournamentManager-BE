package it.manager.tournamentmanager.repositories;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, UUID> {
    List<Tournament> findAllByGame(Game game);
}
