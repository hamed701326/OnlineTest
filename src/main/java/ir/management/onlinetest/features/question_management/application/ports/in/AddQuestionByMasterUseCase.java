package ir.management.onlinetest.features.question_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.AddQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.AddQuestionByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@UseCase
@Secured("ROLE_Master")
public interface AddQuestionByMasterUseCase {
    AddQuestionByMasterOutcome addQuestionByMaster(AddQuestionByMasterCommand command,
                                                   BindingResult result,
                                                   HttpServletRequest request);
}
