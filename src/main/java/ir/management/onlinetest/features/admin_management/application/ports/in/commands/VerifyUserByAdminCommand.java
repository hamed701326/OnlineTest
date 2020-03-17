package ir.management.onlinetest.features.admin_management.application.ports.in.commands;

import ir.management.onlinetest.entities.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VerifyUserByAdminCommand {
    private Long userId;
    private String role;
}
