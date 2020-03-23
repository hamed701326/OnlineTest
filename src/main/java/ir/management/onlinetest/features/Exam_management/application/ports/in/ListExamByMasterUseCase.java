package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.ListExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.ListExamByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@Secured("ROLE_Master")
public interface ListExamByMasterUseCase {
    ListExamByMasterOutcome ListByMaster(ListExamByMasterCommand command,
                                         HttpServletRequest request,
                                         BindingResult bindingResult
                                         );
}
