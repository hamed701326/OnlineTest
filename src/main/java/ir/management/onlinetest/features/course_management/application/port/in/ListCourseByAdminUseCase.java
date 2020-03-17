package ir.management.onlinetest.features.course_management.application.port.in;

import ir.management.onlinetest.features.course_management.application.port.in.command.ListCourseByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListCourseByAdminOutcome;
import org.springframework.security.access.annotation.Secured;


public interface ListCourseByAdminUseCase {
     @Secured("ROLE_Admin")
     ListCourseByAdminOutcome list(ListCourseByAdminCommand command);
}
