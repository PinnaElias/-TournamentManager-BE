package it.manager.tournamentmanager.controllers;

import it.manager.tournamentmanager.entities.Bracket;
import it.manager.tournamentmanager.requests.create.CreateBracketRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateBracketRequestBody;
import it.manager.tournamentmanager.responses.DeleteBracketResponseBody;
import it.manager.tournamentmanager.services.BracketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/brackets")
public class BracketController {

    @Autowired
    private BracketService bracketService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Page<Bracket>> getAllBrackets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Bracket> brackets = bracketService.retrieveAllBrackets(page, size, sortBy);
        return new ResponseEntity<>(brackets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Bracket> getBracketById(@PathVariable UUID id) {
        Bracket bracket = bracketService.retrieveBracketById(id);
        return new ResponseEntity<>(bracket, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Bracket> createBracket(@RequestBody CreateBracketRequestBody bracketRequestBody) {
        Bracket bracket = bracketService.addBracket(bracketRequestBody);
        return new ResponseEntity<>(bracket, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Bracket> updateBracket(
            @PathVariable UUID id,
            @RequestBody UpdateBracketRequestBody bracketRequestBody) {
        Bracket updatedBracket = bracketService.editBracket(id, bracketRequestBody);
        return new ResponseEntity<>(updatedBracket, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<DeleteBracketResponseBody> deleteBracket(@PathVariable UUID id) {
        DeleteBracketResponseBody responseBody = bracketService.removeBracket(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}

