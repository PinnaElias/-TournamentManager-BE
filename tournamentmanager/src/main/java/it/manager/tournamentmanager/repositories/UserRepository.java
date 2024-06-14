package it.manager.tournamentmanager.repositories;

import it.manager.tournamentmanager.entities.User;
import it.manager.tournamentmanager.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByPreferredRole(Role role);
    Optional<User> findByNationality(String nationality);
}
