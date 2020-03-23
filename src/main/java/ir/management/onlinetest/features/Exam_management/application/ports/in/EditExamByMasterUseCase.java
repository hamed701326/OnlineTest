package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.EditExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.EditExamByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@Secured("ROLE_Master")
public interface EditExamByMasterUseCase {
        EditExamByMasterOutcome editByMaster(EditExamByMasterCommand command,
                                             BindingResult result,
                                             HttpServletRequest request);
}
