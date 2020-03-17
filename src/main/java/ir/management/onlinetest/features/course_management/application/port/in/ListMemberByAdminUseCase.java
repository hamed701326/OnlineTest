package ir.management.onlinetest.features.course_management.application.port.in;

import ir.management.onlinetest.features.course_management.application.port.in.command.ListMemberByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListMemberByAdminOutcome;
import org.springframework.security.access.annotation.Secured;

public interface ListMemberByAdminUseCase {
    @Secured("ROLE_Admin")
    ListMemberByAdminOutcome list(ListMemberByAdminCommand command);
}
