package warner.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import warner.domain.contract.UserContract;
import warner.domain.model.User;

@Component
@AllArgsConstructor
public class UserCreator {
    private UserContract userContract;
    public void create(User user) {
        this.userContract.save(user);
    }
}
