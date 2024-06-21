package it.manager.tournamentmanager.services;

import it.manager.tournamentmanager.entities.Game;
import it.manager.tournamentmanager.repositories.GameRepository;
import it.manager.tournamentmanager.requests.create.CreateGameRequestBody;
import it.manager.tournamentmanager.requests.update.UpdateGameRequestBody;
import it.manager.tournamentmanager.responses.DeleteGameResponseBody;
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

    public Game addGame(CreateGameRequestBody gameRequestBody){
        Game gameToCreate = new Game();
        setGameFields(gameToCreate, gameRequestBody);

        return gameRepo.save(gameToCreate);
    }

    public DeleteGameResponseBody removeGame(UUID gameId){
        Game gameToDelete = gameRepo.findById(gameId)
                .orElseThrow(()-> new RuntimeException("Game not found with Id:" + gameId));
        Game gameToShow = new Game();
        setGameFieldsForDeletion(gameToShow, gameToDelete);
        gameRepo.delete(gameToDelete);
        System.out.println(gameToShow);

        return new DeleteGameResponseBody("Game removed successfully", gameToShow);
    }



    public Game editGame(UUID gameId, UpdateGameRequestBody gameRequestBody){
        Game gameToUpdate = gameRepo.findById(gameId)
                .orElseThrow(()-> new RuntimeException("Game not found with Id:" + gameId));

        updateGameFields(gameToUpdate, gameRequestBody);
        return gameRepo.save(gameToUpdate);
    }

     /**
     * HELPER
     * @param gameToUpdate
     * @param gameRequestBody
     */

     public void updateGameFields(Game gameToUpdate, UpdateGameRequestBody gameRequestBody){
         if (gameRequestBody.getName() != null){
             gameToUpdate.setName(gameRequestBody.getName());
         }
         if (gameRequestBody.getAvatar() != null){
             gameToUpdate.setAvatar(gameRequestBody.getAvatar());
         }
         if (gameRequestBody.getMaxPlayersNumberForTeam() != 0){
             gameToUpdate.setMaxPlayersNumberForTeam(gameRequestBody.getMaxPlayersNumberForTeam());
         }
         if (gameRequestBody.getSubscribers() != null){
             gameToUpdate.setSubscribers(gameRequestBody.getSubscribers());
         }
     }

    /**
     * HELPER
     *
     * @param gameToCreate
     * @param gameRequestBody
     */

    public void setGameFields(Game gameToCreate, CreateGameRequestBody gameRequestBody) {
        gameToCreate.setName(gameRequestBody.getName());
        gameToCreate.setAvatar(gameRequestBody.getAvatar());
        gameToCreate.setMaxPlayersNumberForTeam(gameRequestBody.getMaxPlayersNumberForTeam());
    }

    public void setGameFieldsForDeletion(Game gameToCreate, Game gameRequestBody) {
        gameToCreate.setName(gameRequestBody.getName());
        gameToCreate.setAvatar(gameRequestBody.getAvatar());
        gameToCreate.setMaxPlayersNumberForTeam(gameRequestBody.getMaxPlayersNumberForTeam());
        gameToCreate.setSubscribers(gameRequestBody.getSubscribers());
    }
}
