package dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    private String login;
    private String password;
}
