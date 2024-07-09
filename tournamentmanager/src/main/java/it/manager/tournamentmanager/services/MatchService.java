package it.manager.tournamentmanager.services;

import it.manager.tournamentmanager.entities.Match;
import it.manager.tournamentmanager.repositories.MatchRepository;
import it.manager.tournamentmanager.requests.create.CreateMatchRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateMatchRequestBody;
import it.manager.tournamentmanager.responses.DeleteMatchResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepo;

    public Page<Match> retrieveAllMatches(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return matchRepo.findAll(pageable);
    }

    public Match retrieveMatchById(int matchId) {
        return matchRepo.findById(matchId).orElseThrow(() -> new RuntimeException("Match with id: " + matchId + "not found"));
    }

    public Match addMatch(CreateMatchRequestBody matchRequestBody) {
        Match matchToCreate = new Match();
        setMatchFields(matchToCreate, matchRequestBody);

        return matchRepo.save(matchToCreate);
    }

    public DeleteMatchResponseBody removeMatch(int matchId) {
        Match matchToDelete = matchRepo.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with Id:" + matchId));
        Match matchToShow = new Match();
        setMatchFieldsForDeletion(matchToShow, matchToDelete);
        matchRepo.delete(matchToDelete);
        System.out.println(matchToShow);

        return new DeleteMatchResponseBody("Match Removed successfully", matchToShow);
    }

    public Match editMatch(int matchId, UpdateMatchRequestBody matchRequestBody) {
        Match matchToUpdate = matchRepo.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with Id:" + matchId));
        updateMatchFields(matchToUpdate, matchRequestBody);

        return matchRepo.save(matchToUpdate);
    }

    /**
     * HELPER
     *
     * @param matchToCreate
     * @param matchRequestBody
     */

    public void setMatchFields(Match matchToCreate, CreateMatchRequestBody matchRequestBody) {
        matchToCreate.setMatchState(matchRequestBody.getMatchState());
        matchToCreate.setTeamASide(matchRequestBody.getTeamASide());
        matchToCreate.setTeamBSide(matchRequestBody.getTeamBSide());
        matchToCreate.setBracket(matchRequestBody.getBracket());
        matchToCreate.setTournament(matchRequestBody.getTournament());
        matchToCreate.setStartingDate(matchRequestBody.getStartingDate());
        matchToCreate.setStartingTime(matchRequestBody.getStartingTime());
    }

    public void setMatchFieldsForDeletion(Match matchToCreate, Match matchRequestBody) {
        matchToCreate.setMatchState(matchRequestBody.getMatchState());
        matchToCreate.setTeamASide(matchRequestBody.getTeamASide());
        matchToCreate.setTeamBSide(matchRequestBody.getTeamBSide());
        matchToCreate.setBracket(matchRequestBody.getBracket());
        matchToCreate.setTournament(matchRequestBody.getTournament());
        matchToCreate.setStartingDate(matchRequestBody.getStartingDate());
        matchToCreate.setStartingTime(matchRequestBody.getStartingTime());
        matchToCreate.setLoser(matchRequestBody.getLoser());
        matchToCreate.setWinner(matchRequestBody.getWinner());
        matchToCreate.setTeamAScore(matchRequestBody.getTeamAScore());
        matchToCreate.setTeamBScore(matchRequestBody.getTeamBScore());
    }

    /**
     * HELPER
     *
     * @param matchToUpdate
     * @param matchRequestBody
     */

    public void updateMatchFields(Match matchToUpdate, UpdateMatchRequestBody matchRequestBody) {
        if (matchRequestBody.getBracket() != null) {
            matchToUpdate.setBracket(matchRequestBody.getBracket());
        }
        if (matchRequestBody.getTournament() != null) {
            matchToUpdate.setTournament(matchRequestBody.getTournament());
        }
        if (matchRequestBody.getMatchState() != null) {
            matchToUpdate.setMatchState(matchRequestBody.getMatchState());
        }
        if (matchRequestBody.getStartingTime() != null) {
            matchToUpdate.setStartingTime(matchRequestBody.getStartingTime());
        }
        if (matchRequestBody.getStartingDate() != null) {
            matchToUpdate.setStartingDate(matchRequestBody.getStartingDate());
        }
        if (matchRequestBody.getTeamASide() != null) {
            matchToUpdate.setTeamASide(matchRequestBody.getTeamASide());
        }
        if (matchRequestBody.getTeamBSide() != null) {
            matchToUpdate.setTeamBSide(matchRequestBody.getTeamBSide());
        }
        if (matchRequestBody.getWinner() != null) {
            matchToUpdate.setWinner(matchRequestBody.getWinner());
        }
        if (matchRequestBody.getLoser() != null) {
            matchToUpdate.setLoser(matchRequestBody.getLoser());
        }
        if (matchRequestBody.getTeamAScore() != 0) {
            matchToUpdate.setTeamAScore(matchRequestBody.getTeamAScore());
        }
        if (matchRequestBody.getTeamBScore() != 0) {
            matchToUpdate.setTeamBScore(matchRequestBody.getTeamBScore());
        }
    }
}
