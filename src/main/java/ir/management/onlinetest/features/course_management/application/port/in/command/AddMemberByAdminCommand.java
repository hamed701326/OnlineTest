package ir.management.onlinetest.features.course_management.application.port.in.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMemberByAdminCommand {
    private String role;
    private Long courseId;
    private Long userId;
}
