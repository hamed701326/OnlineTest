package ir.management.onlinetest.features.course_management.application.port.in;

import ir.management.onlinetest.features.course_management.application.port.in.command.AddMemberByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.AddMemberByAdminOutcome;
import org.springframework.validation.BindingResult;

public interface AddMemberByAdminUseCase {
    AddMemberByAdminOutcome addMember(AddMemberByAdminCommand command, BindingResult result);
}
