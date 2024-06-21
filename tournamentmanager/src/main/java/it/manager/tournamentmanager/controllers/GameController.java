package it.manager.tournamentmanager.controllers;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.requests.create.CreateGameRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateGameRequestBody;
import it.manager.tournamentmanager.responses.DeleteGameResponseBody;
import it.manager.tournamentmanager.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<Page<Game>> getAllGames(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<Game> games = gameService.retrieveAllGames(page, size, sortBy);
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Game> getGameById(@PathVariable UUID id) {
        Game game = gameService.retrieveGameById(id);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Game> getGameByName(@PathVariable String name) {
        Game game = gameService.retrieveGameByName(name);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Game> createGame(@RequestBody CreateGameRequestBody gameRequestBody) {
        Game game = gameService.addGame(gameRequestBody);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Game> updateGame(
            @PathVariable UUID id,
            @RequestBody UpdateGameRequestBody gameRequestBody) {
        Game updatedGame = gameService.editGame(id, gameRequestBody);
        return new ResponseEntity<>(updatedGame, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<DeleteGameResponseBody> deleteGame(@PathVariable UUID id) {
        DeleteGameResponseBody responseBody = gameService.removeGame(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
