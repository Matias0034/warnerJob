package warner.domain.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import warner.domain.dto.UserCreateDto;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {

    private static final Logger log = LoggerFactory.getLogger(User.class);
    // Constructor por defecto (sin argumentos)
    public User() {
    }

    public User(UserCreateDto userCreateDto) {
        if(Objects.nonNull(userCreateDto.id)){
            this.id = userCreateDto.id;
        }
        Date date = new Date();
        this.email = userCreateDto.email;
        this.password = userCreateDto.password;
        this.name = userCreateDto.name;
        this.isActive = true;
        this.lastLogin = date;
        this.modified = date;
        this.created = date;
        this.phones = userCreateDto.phones;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;


}
