package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.ListExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.ListExamByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;


public interface ListExamByMasterUseCase {
    @Secured("ROLE_Master")
    ListExamByMasterOutcome listByMaster(ListExamByMasterCommand command,
                                         HttpServletRequest request,
                                         BindingResult bindingResult);
}
