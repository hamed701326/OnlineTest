package ir.management.onlinetest.features.admin_management.application.ports.in.commands;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor

public class ListUserByAdminCommand {
    private String status;
    private String userName;
    private String role;
    private List<String> searchAttribute;
}
