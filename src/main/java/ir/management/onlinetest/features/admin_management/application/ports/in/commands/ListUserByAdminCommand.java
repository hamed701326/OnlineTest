package ir.management.onlinetest.features.admin_management.application.ports.in.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString

public class ListUserByAdminCommand {
    private String status;
}
