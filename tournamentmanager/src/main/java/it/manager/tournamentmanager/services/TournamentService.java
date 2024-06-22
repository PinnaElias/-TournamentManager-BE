package it.manager.tournamentmanager.services;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.entities.Tournament;
import it.manager.tournamentmanager.repositories.GameRepository;
import it.manager.tournamentmanager.repositories.TeamRepository;
import it.manager.tournamentmanager.repositories.TournamentRepository;
import it.manager.tournamentmanager.requests.create.CreateTournamentRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateTournamentRequestBody;
import it.manager.tournamentmanager.responses.DeleteTournamentResponseBody;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepo;

    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private GameRepository gameRepo;

    public Page<Tournament> retrieveAllTournaments(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return tournamentRepo.findAll(pageable);
    }

    public Tournament retrieveTournamentById(UUID tournamentId) {
        return tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + tournamentId));
    }

    public List<Tournament> findAllTournamentsByGame(UUID gameId) {
        Game game = gameRepo.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + gameId));
        return tournamentRepo.findAllByGame(game);
    }

    public Tournament addTournament(CreateTournamentRequestBody tournamentRequestBody){
        Tournament tournamentToCreate = new Tournament();
        setTournamentFields(tournamentToCreate, tournamentRequestBody);

        return tournamentRepo.save(tournamentToCreate);
    }

    public Tournament editTournament(UUID tournamentId, UpdateTournamentRequestBody tournamentRequestBody){
        Tournament tournamentToUpdate = tournamentRepo.findById(tournamentId)
                .orElseThrow(()-> new RuntimeException("Tournament not found with Id:" + tournamentId));
        updateTournamentFields(tournamentToUpdate, tournamentRequestBody);
        return tournamentRepo.save(tournamentToUpdate);
    }

    public DeleteTournamentResponseBody removeTournament(UUID tournamentId){
        Tournament tournamentToDelete = tournamentRepo.findById(tournamentId)
                .orElseThrow(()-> new RuntimeException("Tournament not found with Id:" + tournamentId));

        Tournament tournamentToShow = new Tournament();

        setTournamentFieldsForDeletion(tournamentToShow, tournamentToDelete);
        tournamentRepo.delete(tournamentToDelete);
        System.out.println(tournamentToShow);

        return new DeleteTournamentResponseBody("Tournament deleted successfully!", tournamentToShow);
    }

    public Tournament addTeamToTournament(UUID tournamentId, UUID teamId) {
        Tournament tournament = tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + tournamentId));
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));
        tournament.getParticipants().add(team);
        return tournamentRepo.save(tournament);
    }

    public Tournament addLoserTeamToTournament(UUID tournamentId, UUID teamId) {
        Tournament tournament = tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + tournamentId));
        Team team = teamRepo.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + teamId));

        if (!tournament.getParticipants().contains(team)) {
            throw new RuntimeException("This team does not participate in the tournament with Id: " + tournamentId );
        }

        tournament.getLosers().add(team);
        return tournamentRepo.save(tournament);
    }

    @Transactional
    public Tournament setWinner(UUID tournamentId, UUID winnerTeamId) {
        Tournament tournament = tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + tournamentId));

        Team winnerTeam = teamRepo.findById(winnerTeamId)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + winnerTeamId));
        if (!tournament.getParticipants().contains(winnerTeam)) {
            throw new RuntimeException("The team with id " + winnerTeamId + " is not a participant in the tournament with id " + tournamentId);
        }
        if (tournament.getWinner() != null) {
            throw new RuntimeException("A winner is already set for the tournament with id " + tournamentId);
        }
        tournament.setWinner(winnerTeam);

        return tournamentRepo.save(tournament);
    }

    /**
     * HELPER
     *
     * @param tournamentToCreate
     * @param tournamentRequestBody
     */

    public void setTournamentFields(Tournament tournamentToCreate, CreateTournamentRequestBody tournamentRequestBody) {
        tournamentToCreate.setAvatar(tournamentRequestBody.getAvatar());
        tournamentToCreate.setGame(tournamentRequestBody.getGame());
        tournamentToCreate.setName(tournamentRequestBody.getName());
        tournamentToCreate.setBracket(tournamentRequestBody.getBracket());
        tournamentToCreate.setTournamentState(tournamentRequestBody.getTournamentState());
        tournamentToCreate.setDescription(tournamentRequestBody.getDescription());
        tournamentToCreate.setPrize(tournamentRequestBody.getPrize());
        tournamentToCreate.setEndingDate(tournamentRequestBody.getEndingDate());
        tournamentToCreate.setParticipants(tournamentRequestBody.getParticipants());
    }

    public void setTournamentFieldsForDeletion(Tournament tournamentToCreate, Tournament tournamentRequestBody) {
        tournamentToCreate.setTournamentState(tournamentRequestBody.getTournamentState());
        tournamentToCreate.setParticipants(tournamentRequestBody.getParticipants());
        tournamentToCreate.setAvatar(tournamentRequestBody.getAvatar());
        tournamentToCreate.setWinner(tournamentRequestBody.getWinner());
        tournamentToCreate.setGame(tournamentRequestBody.getGame());
        tournamentToCreate.setName(tournamentRequestBody.getName());
        tournamentToCreate.setDescription(tournamentRequestBody.getDescription());
        tournamentToCreate.setParticipants(tournamentRequestBody.getParticipants());
        tournamentToCreate.setLosers(tournamentRequestBody.getLosers());
        tournamentToCreate.setPrize(tournamentRequestBody.getPrize());
        tournamentToCreate.setStartingDate(tournamentRequestBody.getStartingDate());
        tournamentToCreate.setStartingTime(tournamentRequestBody.getStartingTime());
        tournamentToCreate.setEndingDate(tournamentRequestBody.getEndingDate());
        tournamentToCreate.setTournamentManager(tournamentRequestBody.getTournamentManager());
    }

    /**
     * HELPER
     *
     * @param tournamentToUpdate
     * @param tournamentRequestBody
     */

    public void updateTournamentFields(Tournament tournamentToUpdate, UpdateTournamentRequestBody tournamentRequestBody) {
        if (tournamentRequestBody.getBracket() != null) {
            tournamentToUpdate.setBracket(tournamentRequestBody.getBracket());
        }

        if (tournamentRequestBody.getAvatar() != null) {
            tournamentToUpdate.setAvatar(tournamentRequestBody.getAvatar());
        }
        if (tournamentRequestBody.getParticipants() != null) {
            tournamentToUpdate.setParticipants(tournamentRequestBody.getParticipants());
        }
        if (tournamentRequestBody.getDescription() != null) {
            tournamentToUpdate.setDescription(tournamentRequestBody.getDescription());
        }
        if (tournamentRequestBody.getTournamentState() != null) {
            tournamentRequestBody.setTournamentState(tournamentRequestBody.getTournamentState());
        }
        if (tournamentRequestBody.getWinner() != null) {
            tournamentToUpdate.setWinner(tournamentRequestBody.getWinner());
        }
        if (tournamentRequestBody.getGame() != null) {
            tournamentToUpdate.setGame(tournamentRequestBody.getGame());
        }
        if (tournamentRequestBody.getName() != null) {
            tournamentToUpdate.setName(tournamentRequestBody.getName());
        }
        if (tournamentRequestBody.getLosers() != null) {
            tournamentToUpdate.setLosers(tournamentRequestBody.getLosers());
        }
        if (tournamentRequestBody.getPrize() != null) {
            tournamentToUpdate.setPrize(tournamentRequestBody.getPrize());
        }
        if (tournamentRequestBody.getEndingDate() != null) {
            tournamentToUpdate.setEndingDate(tournamentRequestBody.getEndingDate());
        }
    }
}