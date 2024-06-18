package it.manager.tournamentmanager.services;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepo;

    public Page<Game> retrieveAllGames(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return gameRepo.findAll(pageable);
    }

    public Game retrieveGameById(UUID gameId){
        return gameRepo.findById(gameId).orElseThrow(() -> new RuntimeException("Game with id: " + gameId + "not found"));
    }

    public Game retrieveGameByName(String name){
        return gameRepo.retrieveGameByName(name).orElseThrow(() -> new RuntimeException(name + "doesn't exists"));
    }
}
