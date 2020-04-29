package ir.management.onlinetest.features.question_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.ListAllQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.ListAllQuestionByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
@UseCase
public interface ListAllQuestionByMasterUseCase {
    @Secured("ROLE_Master")
    ListAllQuestionByMasterOutcome listByMaster(ListAllQuestionByMasterCommand command,
                                                HttpServletRequest request,
                                                BindingResult result);
}
