package warner.domain.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import warner.domain.model.Phone;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserCreateDto {
    @Nullable
    public UUID id;
    public String email;
    public String password;
    public String name;
    public List<Phone> phones;
}
