package it.manager.tournamentmanager.repositories;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Match;
import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    List<User> findAllByWinner(User winner);
}
