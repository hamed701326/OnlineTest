package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.core.annotation.UseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.ListParticipateInExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.ListParticipateInExamByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
@UseCase
public interface ListParticipateInExamByMasterUseCase {
    @Secured("ROLE_Master")
    ListParticipateInExamByMasterOutcome listByMaster(ListParticipateInExamByMasterCommand command,
                                                      HttpServletRequest request,
                                                      BindingResult result);
}
