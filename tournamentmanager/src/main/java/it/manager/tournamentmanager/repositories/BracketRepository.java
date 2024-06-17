package it.manager.tournamentmanager.repositories;

import it.manager.tournamentmanager.entities.Bracket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BracketRepository extends JpaRepository<Bracket, UUID> {
}
