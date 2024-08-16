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
public class UserReader {
    private static final Logger log = LoggerFactory.getLogger(UserReader.class);
    private UserContract userContract;
    public UserResponseDto read(User user){
        User data = this.userContract.findByEmail(user);
        return new UserResponseDto(data);
    }
}
