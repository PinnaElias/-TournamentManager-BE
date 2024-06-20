package it.manager.tournamentmanager.services;

import it.manager.tournamentmanager.entities.Bracket;
import it.manager.tournamentmanager.repositories.BracketRepository;
import it.manager.tournamentmanager.requests.create.CreateBracketRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateBracketRequestBody;
import it.manager.tournamentmanager.responses.DeleteBracketResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BracketService {

    @Autowired
    private BracketRepository bracketRepo;

    public Page<Bracket> retrieveAllBrackets(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bracketRepo.findAll(pageable);
    }

    public Bracket retrieveBracketById(UUID bracketId) {
        return bracketRepo.findById(bracketId).orElseThrow(() -> new RuntimeException("Customer not found with id: " + bracketId));
    }

    public Bracket addBracket(CreateBracketRequestBody bracketRequestBody){
        Bracket bracketToCreate = new Bracket();
        setBracketFields(bracketToCreate, bracketRequestBody);

        return bracketRepo.save(bracketToCreate);
    }

    public Bracket editBracket(UUID bracketId, UpdateBracketRequestBody bracketRequestBody){
        Bracket bracketToUpdate = bracketRepo.findById(bracketId)
                .orElseThrow(()-> new RuntimeException("Bracket not found with id: " + bracketId));
        updateBracketFields(bracketToUpdate, bracketRequestBody);

        return bracketRepo.save(bracketToUpdate);
    }

    public DeleteBracketResponseBody removeBracket(UUID bracketId){
        Bracket bracketToDelete = bracketRepo.findById(bracketId)
                .orElseThrow(()-> new RuntimeException("Bracket not found with id: " + bracketId));

        Bracket bracketToShow = new Bracket();
        setBracketFieldsForDeletion(bracketToShow, bracketToDelete);
        bracketRepo.delete(bracketToDelete);
        System.out.println(bracketToShow);

        return new DeleteBracketResponseBody("Bracket was deleted successfully!", bracketToShow);
    }

    /**
     * HELPER
     *
     * @param bracketToCreate
     * @param bracketRequestBody
     */

    public void setBracketFields(Bracket bracketToCreate, CreateBracketRequestBody bracketRequestBody) {
        bracketToCreate.setBracketType(bracketRequestBody.getBracketType());
        bracketToCreate.setTournament(bracketRequestBody.getTournament());
        bracketToCreate.setParticipants(bracketRequestBody.getParticipants());
    }

    public void setBracketFieldsForDeletion(Bracket bracketToCreate, Bracket bracketRequestBody) {
        bracketToCreate.setBracketType(bracketRequestBody.getBracketType());
        bracketToCreate.setTournament(bracketRequestBody.getTournament());
        bracketToCreate.setParticipants(bracketRequestBody.getParticipants());
        bracketToCreate.setLosers(bracketRequestBody.getLosers());
        bracketToCreate.setWinner(bracketRequestBody.getWinner());
    }

    /**
     * HELPER
     *
     * @param bracketToUpdate
     * @param bracketRequestBody
     */

    public void updateBracketFields(Bracket bracketToUpdate, UpdateBracketRequestBody bracketRequestBody) {
        if (bracketRequestBody.getBracketType() != null) {
            bracketToUpdate.setBracketType(bracketRequestBody.getBracketType());
        }
        if (bracketRequestBody.getTournament() != null) {
            bracketToUpdate.setTournament(bracketRequestBody.getTournament());
        }
        if (bracketRequestBody.getParticipants() != null) {
            bracketToUpdate.setParticipants(bracketRequestBody.getParticipants());
        }
        if (bracketRequestBody.getLosers() != null) {
            bracketToUpdate.setLosers(bracketRequestBody.getLosers());
        }
        if (bracketRequestBody.getWinner() != null) {
            bracketToUpdate.setWinner(bracketRequestBody.getWinner());
        }
    }
}
