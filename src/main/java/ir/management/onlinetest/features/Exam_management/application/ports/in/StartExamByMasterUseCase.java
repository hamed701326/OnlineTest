package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.StartExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.StartExamByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
@UseCase
public interface StartExamByMasterUseCase {
    @Secured("ROLE_Master")
    StartExamByMasterOutcome startByMaster(StartExamByMasterCommand command,
                                           HttpServletRequest request,
                                           BindingResult result);
}
