package warner.domain.dto;

import warner.domain.model.User;

public class UserResponseDto extends User {

    public UserResponseDto(User user){
        this.setId(user.getId());
        this.setActive(user.isActive());
        this.setCreated(user.getCreated());
        this.setEmail(user.getEmail());
        this.setName(user.getName());
        this.setPhones(user.getPhones());
        this.setModified(user.getModified());
        this.setLastLogin(user.getLastLogin());
    }
}
