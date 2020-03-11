package ir.management.onlinetest.features.user_management.application.ports.in.commands;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpCommand {
    private String userName;
    private String password;
    private String role;
}
