package ir.management.onlinetest.features.admin_management.application.ports.in;

import ir.management.onlinetest.features.admin_management.application.ports.in.commands.ListUserByAdminCommand;
import ir.management.onlinetest.features.admin_management.application.ports.in.outcomes.ListUserByAdminOutcome;
import org.springframework.security.access.annotation.Secured;

public interface ListUserByAdminUseCase {
    @Secured("ROLE_Admin")
    ListUserByAdminOutcome list(ListUserByAdminCommand command) throws Exception;
}
