package warner.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;
    private String citycode;
    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "name")
    private User user;
}
