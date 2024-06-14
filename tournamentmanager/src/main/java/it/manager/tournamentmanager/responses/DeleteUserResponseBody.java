package it.manager.tournamentmanager.responses;

import it.manager.tournamentmanager.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteUserResponseBody {
    private String message;
    private User user;
}
