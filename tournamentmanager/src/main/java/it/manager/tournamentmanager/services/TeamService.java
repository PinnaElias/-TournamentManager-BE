package it.manager.tournamentmanager.services;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.Tournament;
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
        return teamRepo.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found with name: " + teamId));
    }

    public List<Team> findAllTeamsByGame(UUID gameId) {
        Game game = gameRepo.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + gameId));
        return teamRepo.findAllByGame(game);
    }

    public Team addTeam(CreateTeamRequestBody teamRequestBody){
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
        return teamRepo.save(team);
    }

    public Team editTeam(UUID teamId, UpdateTeamRequestBody teamRequestBody){
        Team teamToUpdate = teamRepo.findById(teamId)
                .orElseThrow(()-> new RuntimeException("Team not found with id: " + teamId));
        updateTeamFields(teamToUpdate, teamRequestBody);

        return teamRepo.save(teamToUpdate);
    }

    public DeleteTeamResponseBody removeTeam(UUID teamId){
        Team teamToDelete = teamRepo.findById(teamId)
                .orElseThrow(()-> new RuntimeException("Team not found with id: " + teamId));
        Team teamToShow = new Team();
        setTeamFieldsForDeletion(teamToShow, teamToDelete);
        System.out.println(teamToShow);

        return new DeleteTeamResponseBody("Team deleted successfully", teamToShow);
    }


    /**
     * HELPER
     *
     * @param teamToCreate
     * @param teamRequestBody
     */

    public void setTeamFields(Team teamToCreate, CreateTeamRequestBody teamRequestBody) {
        teamToCreate.setName(teamRequestBody.getName());
        teamToCreate.setMembers(teamRequestBody.getMembers());
        teamToCreate.setGame(teamRequestBody.getGame());
        teamToCreate.setAvatar(teamRequestBody.getAvatar());
        teamToCreate.setNationality(teamRequestBody.getNationality());
    }

    public void setTeamFieldsForDeletion(Team teamToCreate, Team teamRequestBody) {
        teamToCreate.setName(teamRequestBody.getName());
        teamToCreate.setMembers(teamRequestBody.getMembers());
        teamToCreate.setGame(teamRequestBody.getGame());
        teamToCreate.setAvatar(teamRequestBody.getAvatar());
        teamToCreate.setNationality(teamRequestBody.getNationality());
        teamToCreate.setActiveTournaments(teamRequestBody.getActiveTournaments());
        teamToCreate.setTournamentsHistory(teamRequestBody.getTournamentsHistory());
    }

    /**
     * HELPER
     *
     * @param teamToUpdate
     * @param teamRequestBody
     */

    public void updateTeamFields(Team teamToUpdate, UpdateTeamRequestBody teamRequestBody) {
        if (teamRequestBody.getName() != null) {
            teamToUpdate.setName(teamRequestBody.getName());
        }
        if (teamRequestBody.getMembers() != null) {
            teamToUpdate.setMembers(teamRequestBody.getMembers());
        }
        if (teamRequestBody.getGame() != null) {
            teamToUpdate.setGame(teamRequestBody.getGame());
        }
        if (teamRequestBody.getAvatar() != null) {
            teamToUpdate.setAvatar(teamRequestBody.getAvatar());
        }
        if (teamRequestBody.getNationality() != null) {
            teamToUpdate.setNationality(teamRequestBody.getNationality());
        }
        if (teamRequestBody.getActiveTournaments() != null) {
            teamToUpdate.setActiveTournaments(teamRequestBody.getActiveTournaments());
        }
        if (teamRequestBody.getTournamentsHistory() != null) {
            teamToUpdate.setTournamentsHistory(teamRequestBody.getTournamentsHistory());
        }
    }
}
