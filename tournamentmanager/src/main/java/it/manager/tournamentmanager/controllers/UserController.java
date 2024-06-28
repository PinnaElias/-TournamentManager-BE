package it.manager.tournamentmanager.controllers;

import it.manager.tournamentmanager.entities.User;
import it.manager.tournamentmanager.requests.update.UpdateUserRequestBody;
import it.manager.tournamentmanager.responses.DeleteUserResponseBody;
import it.manager.tournamentmanager.services.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Page<User>> getUsers(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(userService.retrieveAllUsers(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return new ResponseEntity<>(userService.retrieveUserById(id), HttpStatus.OK);
    }

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('USER','ADMIN')")
    public ResponseEntity<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody @Validated UpdateUserRequestBody userRequestBody,
                                           @PathVariable UUID id,
                                           BindingResult validation) throws BadRequestException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (acc, curr) -> acc+curr));
        }
        return new ResponseEntity<>(userService.editUser(id, userRequestBody), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DeleteUserResponseBody> deleteUser(@PathVariable UUID id) {
        return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);
    }
}
