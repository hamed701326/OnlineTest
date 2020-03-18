package ir.management.onlinetest.features.course_management.application.port.in.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveMemberByAdminCommand {
    private Long userId;
    private  Long courseId;
}
