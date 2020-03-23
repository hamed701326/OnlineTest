package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.CreateExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.CreateExamByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@Secured("ROLE_Master")
public interface CreateExamByMasterUseCase {
    CreateExamByMasterOutcome createByMaster(CreateExamByMasterCommand command,
                                             HttpServletRequest request,
                                             BindingResult result);
}
