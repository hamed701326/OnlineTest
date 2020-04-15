package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.TakeExamByStudentCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.TakeExamByStudentOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

public interface TakeExamByStudentUseCase {
    @Secured("ROLE_Student")
    TakeExamByStudentOutcome takeByStudent(TakeExamByStudentCommand command,
                                           HttpServletRequest request,
                                           BindingResult result);
}
