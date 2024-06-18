package it.manager.tournamentmanager.repositories;

import it.manager.tournamentmanager.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    @Query("SELECT g FROM Game g WHERE LOWER(g.name) = LOWER(:name)")
    public Optional<Game> retrieveGameByName(@Param("name") String name);
}
