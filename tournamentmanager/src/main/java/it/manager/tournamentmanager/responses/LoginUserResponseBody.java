package it.manager.tournamentmanager.responses;

import it.manager.tournamentmanager.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserResponseBody {
    private User user;

    private String message;

    private String token;
}
