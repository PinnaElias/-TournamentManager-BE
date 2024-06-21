package it.manager.tournamentmanager.controllers;

import it.manager.tournamentmanager.entities.Team;
import it.manager.tournamentmanager.requests.create.CreateTeamRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateTeamRequestBody;
import it.manager.tournamentmanager.responses.DeleteTeamResponseBody;
import it.manager.tournamentmanager.services.AuthService;
import it.manager.tournamentmanager.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<Page<Team>> getAllTeams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        Page<Team> teams = teamService.retrieveAllTeams(page, size, sortBy);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Team> getTeamById(@PathVariable UUID id) {
        Team team = teamService.retrieveTeamById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Team>> getTeamsByGame(@PathVariable UUID gameId) {
        List<Team> teams = teamService.findAllTeamsByGame(gameId);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Team> createTeam(@RequestBody CreateTeamRequestBody teamRequestBody) {
        Team team = teamService.addTeam(teamRequestBody);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @PutMapping("/{teamId}/addUser/{userId}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Team> addUserToTeam(@PathVariable UUID teamId, @PathVariable UUID userId) {
        Team team = teamService.addUserToTeam(teamId, userId);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<Team> updateTeam(
            @PathVariable UUID id,
            @RequestBody UpdateTeamRequestBody teamRequestBody) {
        Team updatedTeam = teamService.editTeam(id, teamRequestBody);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<DeleteTeamResponseBody> deleteTeam(@PathVariable UUID id) {
        DeleteTeamResponseBody responseBody = teamService.removeTeam(id);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}

