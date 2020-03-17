package ir.management.onlinetest.features.course_management.application.port.in;

import ir.management.onlinetest.features.course_management.application.port.in.command.AddCourseByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.AddCourseByAdminOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

public interface AddCourseByAdminUseCase {
    @Secured("ROLE_Admin")
    AddCourseByAdminOutcome addCourse(AddCourseByAdminCommand command, BindingResult result);
}
