package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.ListExamByStudentCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.ListExamByStudentOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@UseCase

public interface ListExamByStudentUseCase {
    @Secured("ROLE_Student")
    ListExamByStudentOutcome listByStudent(ListExamByStudentCommand command,
                                           BindingResult result,
                                           HttpServletRequest request);
}
