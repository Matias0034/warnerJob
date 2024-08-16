package warner.application;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import warner.domain.contract.UserContract;
import warner.domain.dto.UserResponseDto;
import warner.domain.model.User;

@Component
@AllArgsConstructor
public class UserFirstAccesReader {
    private static final Logger log = LoggerFactory.getLogger(UserFirstAccesReader.class);
    private UserContract userContract;
    public User read(User user){
        return this.userContract.findByEmail(user);
    }
}
