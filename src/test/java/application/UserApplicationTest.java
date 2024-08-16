package application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import warner.application.UserCreator;
import warner.application.UserReader;
import warner.application.UserVerifier;
import warner.domain.contract.UserContract;
import warner.domain.model.User;

import java.util.Optional;

class UserApplicationTest {

    @Mock
    private UserContract userContract;

    @InjectMocks
    private UserReader userReader;

    @InjectMocks
    private UserVerifier userVerifier;

    @InjectMocks
    private UserCreator userCreator;

    private User testUser;
    private User expectedUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        expectedUser = new User();
        testUser.setEmail("test@example.com");
        expectedUser.setEmail("test@example.com");
    }

    @Test
    void shouldReturnUserWhenFound() {
        when(userContract.findByEmail(any(User.class))).thenReturn(expectedUser);
        User result = userReader.read(testUser);
        assertEquals(expectedUser, result);
    }

    @Test
    void shouldThrowBadRequestExceptionWhenUserExists() {
        when(userContract.verifyByEmail(testUser)).thenReturn(Optional.of(testUser));
        assertThrows(BadRequestException.class, () -> userVerifier.verify(testUser));
    }

    @Test
    void shouldNotThrowExceptionWhenUserDoesNotExist() throws BadRequestException {
        when(userContract.verifyByEmail(testUser)).thenReturn(Optional.empty());
        userVerifier.verify(testUser);
    }


    @Test
    void shouldCallSaveMethodOnUserContract() {
        userCreator.create(testUser);
        verify(userContract, times(1)).save(testUser);
    }
}
