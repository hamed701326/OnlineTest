package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.CorrectFreeQuestionByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.CorrectFreeQuestionByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
@UseCase
public interface CorrectFreeQuestionByMasterUseCase {
    @Secured("ROLE_Master")
    CorrectFreeQuestionByMasterOutcome correctByMaster(CorrectFreeQuestionByMasterCommand command,
                                                       BindingResult result,
                                                       HttpServletRequest request);
}
