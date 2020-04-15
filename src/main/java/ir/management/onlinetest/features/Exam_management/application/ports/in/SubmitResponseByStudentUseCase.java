package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.SubmitResponseByStudentCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.SubmitResponseByStudentOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
@UseCase
public interface SubmitResponseByStudentUseCase {
    @Secured("ROLE_Student")
    SubmitResponseByStudentOutcome submitByStudent(SubmitResponseByStudentCommand command,
                                                   HttpServletRequest request,
                                                   BindingResult result);
}
