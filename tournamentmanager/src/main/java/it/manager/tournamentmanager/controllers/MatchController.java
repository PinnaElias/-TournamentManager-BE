package it.manager.tournamentmanager.controllers;

import it.manager.tournamentmanager.entities.Match;
import it.manager.tournamentmanager.requests.create.CreateMatchRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateMatchRequestBody;
import it.manager.tournamentmanager.responses.DeleteMatchResponseBody;
import it.manager.tournamentmanager.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Page<Match>> getAllMatches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Match> matches = matchService.retrieveAllMatches(page, size, sortBy);
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Match> getMatchById(@PathVariable int id) {
        Match match = matchService.retrieveMatchById(id);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Match> createMatch(@RequestBody CreateMatchRequestBody matchRequestBody) {
        Match match = matchService.addMatch(matchRequestBody);
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Match> updateMatch(
            @PathVariable int id,
            @RequestBody UpdateMatchRequestBody matchRequestBody) {
        Match updatedMatch = matchService.editMatch(id, matchRequestBody);
        return new ResponseEntity<>(updatedMatch, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<DeleteMatchResponseBody> deleteMatch(@PathVariable int id) {
        DeleteMatchResponseBody responseBody = matchService.removeMatch(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
