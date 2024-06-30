package it.manager.tournamentmanager.services;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.User;
import it.manager.tournamentmanager.repositories.GameRepository;
import it.manager.tournamentmanager.repositories.TeamRepository;
import it.manager.tournamentmanager.repositories.UserRepository;
import it.manager.tournamentmanager.requests.create.CreateTeamRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateTeamRequestBody;
import it.manager.tournamentmanager.responses.DeleteTeamResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private UserRepository userRepo;

    public Page<Team> retrieveAllTeams(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return teamRepo.findAll(pageable);
    }

    public Team retrieveTeamById(UUID teamId) {
        return teamRepo.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));
    }

    public List<Team> findAllTeamsByGame(UUID gameId) {
        Game game = gameRepo.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + gameId));
        return teamRepo.findAllByGame(game);
    }

    public Team addTeam(CreateTeamRequestBody teamRequestBody) {
        Team teamToCreate = new Team();
        setTeamFields(teamToCreate, teamRequestBody);
        return teamRepo.save(teamToCreate);
    }

    public Team addUserToTeam(UUID teamId, UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));

        team.getMembers().add(user);
        user.setTeam(team);
        userRepo.save(user);
        return teamRepo.save(team);
    }

    public Team editTeam(UUID teamId, UpdateTeamRequestBody teamRequestBody) {
        Team teamToUpdate = teamRepo.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));
        updateTeamFields(teamToUpdate, teamRequestBody);
        return teamRepo.save(teamToUpdate);
    }

    public DeleteTeamResponseBody removeTeam(UUID teamId) {
        Team teamToDelete = teamRepo.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));
        teamRepo.delete(teamToDelete);
        return new DeleteTeamResponseBody("Team deleted successfully", teamToDelete);
    }

    /**
     * HELPER
     *
     * @param team
     * @param teamRequestBody
     */
    private void setTeamFields(Team team, CreateTeamRequestBody teamRequestBody) {
        team.setName(teamRequestBody.getName());
        team.setMembers(teamRequestBody.getMembers());
        team.setGame(teamRequestBody.getGame());
        team.setAvatar(teamRequestBody.getAvatar());
        team.setNationality(teamRequestBody.getNationality());
    }

    private void updateTeamFields(Team team, UpdateTeamRequestBody teamRequestBody) {
        if (teamRequestBody.getName() != null) {
            team.setName(teamRequestBody.getName());
        }
        if (teamRequestBody.getMembers() != null) {
            for (User user : teamRequestBody.getMembers()) {
                user.setTeam(team);
                userRepo.save(user);
            }
            team.setMembers(teamRequestBody.getMembers());
        }
        if (teamRequestBody.getGame() != null) {
            team.setGame(teamRequestBody.getGame());
        }
        if (teamRequestBody.getAvatar() != null) {
            team.setAvatar(teamRequestBody.getAvatar());
        }
        if (teamRequestBody.getNationality() != null) {
            team.setNationality(teamRequestBody.getNationality());
        }
        if (teamRequestBody.getActiveTournaments() != null) {
            team.setActiveTournaments(teamRequestBody.getActiveTournaments());
        }
        if (teamRequestBody.getTournamentsHistory() != null) {
            team.setTournamentsHistory(teamRequestBody.getTournamentsHistory());
        }
    }
}
