package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.FinishExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.FinishExamByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@UseCase
public interface FinishExamByMasterUseCase {
    @Secured("ROLE_Master")
    FinishExamByMasterOutcome finishByMaster(FinishExamByMasterCommand command,
                                             HttpServletRequest request,
                                             BindingResult result);
}
