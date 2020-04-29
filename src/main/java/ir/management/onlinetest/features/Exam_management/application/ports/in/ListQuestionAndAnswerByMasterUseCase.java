package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.ListQuestionAndAnswerByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.ListQuestionAndAnswerByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
@UseCase
public interface ListQuestionAndAnswerByMasterUseCase {
    @Secured("ROLE_Master")
    ListQuestionAndAnswerByMasterOutcome listByMaster(ListQuestionAndAnswerByMasterCommand command,
                                                      BindingResult result,
                                                      HttpServletRequest request);
}
