package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.SetLeftTimeForTakeExamCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.SetLeftTimeForTakeExamOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@UseCase
public interface SetLeftTimeForTakeExamUseCase {
    @Secured("ROLE_Student")
    SetLeftTimeForTakeExamOutcome setLeftTime(SetLeftTimeForTakeExamCommand command,
                                              BindingResult result,
                                              HttpServletRequest request);
}
