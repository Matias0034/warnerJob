package warner.domain.contract;

import warner.domain.model.User;

import java.util.Optional;

public interface UserContract {
    User findByEmail(User user);
    User save(User user);
    Optional<User> verifyByEmail(User user);
    Optional<User> verifyByIdAndEmail(User user);
}
