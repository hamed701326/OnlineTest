package ir.management.onlinetest.features.admin_management.application.ports.in;

import ir.management.onlinetest.features.admin_management.application.ports.in.commands.VerifyUserByAdminCommand;
import ir.management.onlinetest.features.admin_management.application.ports.in.outcomes.VerifyUserByAdminOutcome;
import org.springframework.security.access.annotation.Secured;

public interface VerifyUserByAdminUseCase {
    @Secured("ROLE_Admin")
    VerifyUserByAdminOutcome verify(VerifyUserByAdminCommand verifyUserByAdminCommand) throws Exception;
}
