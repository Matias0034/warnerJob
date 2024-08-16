package warner.domain.model;

import org.apache.coyote.BadRequestException;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Email{
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public Email(String email) throws BadRequestException {
        if(!this.returnIsValidEmail(email)){
            throw new BadRequestException("El correo no cumple con el formato");
        }
    }

    private boolean returnIsValidEmail(String email) {
        if (email == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
