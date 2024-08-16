package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import warner.domain.dto.UserCreateDto;
import warner.domain.model.Phone;
import warner.domain.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class UserTest {

    @Test
    void shouldCreateUserFromDto() {
        List<Phone> phones = new ArrayList<>();
        UserCreateDto userCreateDto = new UserCreateDto(null, "test@example.com", "password123", "Test User", phones);
        userCreateDto.setPhones(List.of(new Phone()));

        User user = new User(userCreateDto);

        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("Test User", user.getName());
        assertTrue(user.isActive());
        assertEquals(userCreateDto.getPhones(), user.getPhones());

        Date now = new Date();
        assertTrue(user.getCreated().getTime() <= now.getTime());
        assertTrue(user.getModified().getTime() <= now.getTime());
        assertTrue(user.getLastLogin().getTime() <= now.getTime());
    }

    @Test
    void shouldUpdateUserFields() {
        User user = new User();
        user.setName("Old Name");
        user.setEmail("old@example.com");

        user.setName("New Name");
        user.setEmail("new@example.com");

        assertEquals("New Name", user.getName());
        assertEquals("new@example.com", user.getEmail());
    }
}
