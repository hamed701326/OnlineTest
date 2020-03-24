package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.DeleteExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.DeleteExamByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@Secured("ROLE_Master")
public interface DeleteExamByMasterUseCase {
    DeleteExamByMasterOutcome deleteByMaster(DeleteExamByMasterCommand command,
                                             BindingResult result,
                                             HttpServletRequest request);
}
