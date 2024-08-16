package controller;

import com.sun.tools.javac.Main;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import warner.application.*;
import warner.domain.dto.UserCreateDto;
import warner.domain.dto.UserResponseDto;
import warner.domain.model.Phone;
import warner.domain.model.User;
import org.mockito.Mockito;
import warner.infrastructure.controller.UserController;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes =  Main.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Mock
    private UserCreator userCreator;

    @InjectMocks
    private UserController userController;

    @Mock
    private TokenGenerator tokenGenerator;

    @Mock
    private UserVerifier userVerifier;
    @Mock
    private UserReader userReader;
    @Mock
    private UserFirstAccesReader userFirstAccesReader;

    private UserCreateDto userCreateDto;

    private User user;

    @BeforeEach
    void setUp() {
        List<Phone> phones = new ArrayList<>();
        // Inicialización de los objetos necesarios para las pruebas
        userCreateDto = new UserCreateDto(null,"test@example.com","TestUser","password123", phones);
        user = new User(userCreateDto);
    }

    @Test
    @DisplayName("Should return status Created")
    void createUserCorrect() throws BadRequestException {
        Mockito.doNothing().when(tokenGenerator).generateJWTToken(Mockito.any(User.class));
        Mockito.doNothing().when(userVerifier).verify(Mockito.any(User.class));
        Mockito.doNothing().when(userCreator).create(Mockito.any(User.class));
        UserResponseDto userReturned = new UserResponseDto(user);
        userReturned.setEmail(user.getEmail());
        userReturned.setName(user.getName());
        when(userFirstAccesReader.read(user)).thenReturn(userReturned);
        ResponseEntity<User> responseDto = userController.registerUser(userCreateDto);
        assertThat(responseDto.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }


    @Test
    @DisplayName("Should return sataus OK")
    void readUserStatusOK(){
        UserResponseDto userReturned = new UserResponseDto(user);
        userReturned.setEmail(user.getEmail());
        userReturned.setName(user.getName());

        when(userReader.read(user)).thenReturn(userReturned);
        ResponseEntity<User> responseDto = userController.getUser(user.getEmail());
        assertThat(responseDto.getStatusCode()).isEqualTo(HttpStatus.OK);

    }


}
