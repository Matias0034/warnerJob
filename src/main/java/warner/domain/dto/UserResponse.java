package warner.domain.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserResponse {

    private String timestamp;
    private Integer code;
    private String message;

}