package ir.management.onlinetest.features.course_management.application.port.in;

import ir.management.onlinetest.features.course_management.application.port.in.command.ListCourseByMasterCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListCourseByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

public interface ListCourseByMasterUseCase {
    @Secured("ROLE_Master")
    ListCourseByMasterOutcome listByMaster(ListCourseByMasterCommand command, BindingResult bindingResult, HttpServletRequest request);
}
