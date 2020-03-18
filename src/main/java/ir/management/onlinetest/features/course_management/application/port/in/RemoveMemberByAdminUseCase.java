package ir.management.onlinetest.features.course_management.application.port.in;

import ir.management.onlinetest.features.course_management.application.port.in.command.RemoveMemberByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.RemoveMemberByAdminOutcome;
import org.springframework.security.access.annotation.Secured;

@Secured("ROLE_Admin")
public interface RemoveMemberByAdminUseCase {
    RemoveMemberByAdminOutcome remove(RemoveMemberByAdminCommand command);
}
