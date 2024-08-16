package warner.infrastructure.controller;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import warner.application.*;
import warner.domain.dto.UserCreateDto;
import warner.domain.dto.UserResponseDto;
import warner.domain.model.Email;
import warner.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserCreator userCreator;
    private final UserReader userReader;
    private final UserUpdater userUpdater;
    private final UserFirstAccesReader userFirstAccesReader;
    private final TokenGenerator tokenGenerator;
    private final UserVerifier userVerifier;

    /**
     * Registra usuario
     * @param userCreateDto información del usuario
     * @return JSON con el usuario registrado
     */
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserCreateDto userCreateDto) throws BadRequestException {


            User user = new User(userCreateDto);
            this.userVerifier.verify(user);
            this.tokenGenerator.generateJWTToken(user);
            this.userCreator.create(user);
            User response = this.userFirstAccesReader.read(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


    /**
     * Obtiene usuario mediante email
     * @param email  email del usuario
     * @return JSON con el usuario registrado
     */
    @GetMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@RequestParam String email){
        User user = new User();
        user.setEmail(email);
        User response = this.userReader.read(user);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    /**
     * Actualiza usuario
     * @param userCreateDto información del usuario
     * @return JSON con el usuario registrado
     */
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserCreateDto userCreateDto) throws BadRequestException {
        User user = new User(userCreateDto);
        this.userUpdater.update(user);
        UserResponseDto response = this.userReader.read(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
