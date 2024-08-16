package warner.application;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;
import warner.domain.contract.UserContract;
import warner.domain.model.User;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserUpdater {
    private UserContract userContract;
    public void update(User user) throws BadRequestException {
        if(this.userContract.verifyByIdAndEmail(user).isEmpty()){
            throw new BadRequestException("Este usuario no existe");
        };
        this.userContract.save(user);
    }
}
