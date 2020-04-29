package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.FinishCorrectionByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.FinishCorrectionByMasterOutcome;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface FinishCorrectionByMasterUseCase {
    @Secured("ROLE_Master")
    FinishCorrectionByMasterOutcome finishCorrectionByMaster(FinishCorrectionByMasterCommand command,
                                                             BindingResult result ,
                                                             HttpServletRequest request);
}
