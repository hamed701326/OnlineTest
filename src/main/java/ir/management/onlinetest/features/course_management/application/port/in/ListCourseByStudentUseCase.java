package ir.management.onlinetest.features.course_management.application.port.in;

import ir.management.onlinetest.features.course_management.application.port.in.command.ListCourseByStudentCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListCourseByMasterOutcome;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListCourseByStudentOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
@Secured("ROLE_Student")
public interface ListCourseByStudentUseCase {
    ListCourseByStudentOutcome listByStudent(ListCourseByStudentCommand command,
                                             BindingResult bindingResult,
                                             HttpServletRequest request);
}
