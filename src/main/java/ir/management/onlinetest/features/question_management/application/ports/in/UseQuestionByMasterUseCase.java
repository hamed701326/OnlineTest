package ir.management.onlinetest.features.question_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.UseQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.UseQuestionByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

@UseCase
@Secured("ROLE_Master")
public interface UseQuestionByMasterUseCase {
    UseQuestionByMasterOutcome useFromQuestionBank(UseQuestionByMasterCommand command,
                                                   BindingResult result,
                                                   HttpServletRequest request);
}
