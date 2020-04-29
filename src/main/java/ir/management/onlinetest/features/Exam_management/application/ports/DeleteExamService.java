package ir.management.onlinetest.features.Exam_management.application.ports;

import ir.management.onlinetest.features.Exam_management.application.ports.in.DeleteExamByMasterUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.DeleteExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.DeleteExamByMasterOutcome;
import ir.management.onlinetest.repositories.ExamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Service
public class DeleteExamService implements DeleteExamByMasterUseCase {
    private final ExamRepository examRepository;

    public DeleteExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public DeleteExamByMasterOutcome deleteByMaster(DeleteExamByMasterCommand command,
                                                    BindingResult result,
                                                    HttpServletRequest request) {
        Long masterId= (Long) request.getSession().getAttribute("accountId");
        DeleteExamByMasterOutcome response=new DeleteExamByMasterOutcome();
        response.setValid(true);
        if(result.hasErrors()){
            response.setValid(false);
            response.setErrorMessages(
                    result
                            .getFieldErrors()
                            .stream()
                            .collect(Collectors
                                    .toMap(FieldError::getField, FieldError::getDefaultMessage)
                            )
            );
        }
        else {
            examRepository.findById(command.getExamId()).ifPresent(
                    exam -> {
                        if (exam.getAccount().getId().equals(masterId)) {
                            examRepository.delete(exam);
                            examRepository.flush();
                            response.setValid(true);
                        } else {
                            response.setValid(false);
                            response.getErrorMessages().put("NotExistThisTestForMaster",
                                    "This Test is not yours.");
                        }
                    }
            );
        }
        return response;
    }
}
