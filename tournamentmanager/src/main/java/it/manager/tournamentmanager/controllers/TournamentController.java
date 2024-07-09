package it.manager.tournamentmanager.controllers;

import it.manager.tournamentmanager.entities.*;
import it.manager.tournamentmanager.repositories.BracketRepository;
import it.manager.tournamentmanager.repositories.GameRepository;
import it.manager.tournamentmanager.repositories.TeamRepository;
import it.manager.tournamentmanager.repositories.UserRepository;
import it.manager.tournamentmanager.requests.create.CreateTournamentRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateTournamentRequestBody;
import it.manager.tournamentmanager.responses.DeleteTournamentResponseBody;
import it.manager.tournamentmanager.services.TournamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private BracketRepository bracketRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private TeamRepository teamRepo;

    @GetMapping
    public ResponseEntity<Page<Tournament>> getAllTournaments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        Page<Tournament> tournaments = tournamentService.retrieveAllTournaments(page, size, sortBy);
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable UUID id) {
        Tournament tournament = tournamentService.retrieveTournamentById(id);
        return ResponseEntity.ok(tournament);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Tournament>> getAllTournamentsByGame(@PathVariable UUID gameId) {
        List<Tournament> tournaments = tournamentService.findAllTournamentsByGame(gameId);
        return ResponseEntity.ok(tournaments);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Tournament> addTournament(@RequestBody CreateTournamentRequestBody tournamentRequestBody,
                                                    @AuthenticationPrincipal User me) {

        Tournament tournament = new Tournament();
//      Segue la conversione degli Id in entità

        List<Team> participants = teamRepo.findAllById(tournamentRequestBody.participants());
        tournament.setAvatar(tournamentRequestBody.avatar());
        tournament.setGame(tournamentRequestBody.game());
        tournament.setName(tournamentRequestBody.name());
        tournament.setParticipants(participants);
//        tournament.setBracket(bracket);
        tournament.setTournamentState(tournamentRequestBody.tournamentState());
        tournament.setTournamentManager(me);
        tournament.setDescription(tournamentRequestBody.description());
        tournament.setPrize(tournamentRequestBody.prize());
        tournament.setStartingDate(tournamentRequestBody.startingDate());
        tournament.setEndingDate(tournamentRequestBody.endingDate());
        tournament.setStartingTime(tournamentRequestBody.startingTime());
//        Strumento di debug
//        System.out.println("Request Body: " + tournamentRequestBody);
        Tournament createdTournament = tournamentService.addTournament(tournamentRequestBody);
        return new ResponseEntity<>(createdTournament, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Tournament> updateTournament(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateTournamentRequestBody tournamentRequestBody) {
        Tournament updatedTournament = tournamentService.editTournament(id, tournamentRequestBody);
        return ResponseEntity.ok(updatedTournament);
    }

    @DeleteMapping("/{tournamentId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<DeleteTournamentResponseBody> deleteTournament(@PathVariable UUID tournamentId) {
        DeleteTournamentResponseBody response = tournamentService.removeTournament(tournamentId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{tournamentId}/add-team/{teamId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Tournament> addTeamToTournament(
            @PathVariable UUID tournamentId,
            @PathVariable UUID teamId) {
        Tournament updatedTournament = tournamentService.addTeamToTournament(tournamentId, teamId);
        return ResponseEntity.ok(updatedTournament);
    }

    @PostMapping("/{tournamentId}/add-loser/{teamId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Tournament> addLoserTeamToTournament(
            @PathVariable UUID tournamentId,
            @PathVariable UUID teamId) {
        Tournament updatedTournament = tournamentService.addLoserTeamToTournament(tournamentId, teamId);
        return ResponseEntity.ok(updatedTournament);
    }

    @PostMapping("/{tournamentId}/set-winner/{winnerTeamId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Tournament> setWinner(
            @PathVariable UUID tournamentId,
            @PathVariable UUID winnerTeamId) {
        Tournament updatedTournament = tournamentService.setWinner(tournamentId, winnerTeamId);
        return ResponseEntity.ok(updatedTournament);
    }
}
