package it.manager.tournamentmanager.services;

import it.manager.tournamentmanager.entities.Match;
import it.manager.tournamentmanager.entities.Tournament;
import it.manager.tournamentmanager.repositories.TournamentRepository;
import it.manager.tournamentmanager.requests.create.CreateMatchRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateMatchRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateTournamentRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepo;

    public Page<Tournament> retrieveAllTournaments(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return tournamentRepo.findAll(pageable);
    }

    public Tournament retrieveCustomerById(UUID tournamentId) {
        return tournamentRepo.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + tournamentId));
    }


    /**
     * HELPER
     *
     * @param tournamentToCreate
     * @param tournamentRequestBody
     */

    public void setTournamentFields(Tournament tournamentToCreate, UpdateTournamentRequestBody tournamentRequestBody) {
        tournamentToCreate.setAvatar(tournamentRequestBody.getAvatar());
        tournamentToCreate.setGame(tournamentRequestBody.getGame());
        tournamentToCreate.setName(tournamentRequestBody.getName());
        tournamentToCreate.setBracket(tournamentRequestBody.getBracket());
        tournamentToCreate.setTournamentState(tournamentRequestBody.getTournamentState());
        tournamentToCreate.setDescription(tournamentRequestBody.getDescription());
        tournamentToCreate.setPrize(tournamentRequestBody.getPrize());
        tournamentToCreate.setEndingDate(tournamentRequestBody.getEndingDate());
        tournamentToCreate.setWinner(tournamentRequestBody.getWinner());
        tournamentToCreate.setParticipants(tournamentRequestBody.getParticipants());
        tournamentToCreate.setLosers(tournamentRequestBody.getLosers());
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
