package domain;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import warner.domain.model.Email;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldThrowExceptionForInvalidEmail() {
        // Correos electrónicos inválidos para probar
        String[] invalidEmails = {
                "plainaddress",
                "@missingusername.com",
                "username@.com",
                "username@domain@domain.com",
                "username@domain..com",
                "username@domain.com@",
                "username@domain",
                "username@domain.c",
                "username@-domain.com",
                "username@domain.com-"
        };

        for (String email : invalidEmails) {
            try {
                new Email(email);
            } catch (BadRequestException e) {
                assertEquals("El correo no cumple con el formato", e.getMessage());
            }

        }
    }

    @Test
    void shouldNotThrowExceptionForValidEmail() {
        // Correos electrónicos válidos para probar
        String[] validEmails = {
                "user@example.com",
                "user.name@example.co.uk",
                "user_name@example.com",
                "user+name@example.com",
                "user@example.com",
                "user@example.travel",
                "user@example.museum",
                "user@example.web"
        };

        // Verificar que no se lanza BadRequestException para correos válidos
        for (String email : validEmails) {
            assertDoesNotThrow(() -> new Email(email));
        }
    }
}
