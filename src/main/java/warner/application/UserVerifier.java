package warner.application;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import warner.domain.contract.UserContract;
import warner.domain.model.Email;
import warner.domain.model.User;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserVerifier {
    private static final Logger log = LoggerFactory.getLogger(UserVerifier.class);
    private UserContract userContract;

    public void verify(User user) throws BadRequestException {
        new Email(user.getEmail());
        Optional<User> response = this.userContract.verifyByEmail(user);
        if(response.isPresent()){
            throw new BadRequestException("Correo ya registrado");
        }
    }


}
