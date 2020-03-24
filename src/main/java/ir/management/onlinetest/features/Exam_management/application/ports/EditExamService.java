package ir.management.onlinetest.features.Exam_management.application.ports;

import ir.management.onlinetest.features.Exam_management.application.ports.in.EditExamByMasterUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.EditExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.EditExamByMasterOutcome;
import ir.management.onlinetest.repositories.ExamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
@Service
public class EditExamService implements EditExamByMasterUseCase {
    private  final ExamRepository examRepository;

    public EditExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public EditExamByMasterOutcome editByMaster(EditExamByMasterCommand command,
                                                BindingResult result,
                                                HttpServletRequest request) {
        EditExamByMasterOutcome response=new EditExamByMasterOutcome();
        response.setValid(true);
        Long masterId= (Long) request.getSession().getAttribute("accountId");
        if(result.hasErrors()){
            response.setValid(false);
            response.setErrorMessage(
                    result
                            .getFieldErrors()
                            .stream()
                            .collect(Collectors
                                    .toMap(FieldError::getField, FieldError::getDefaultMessage)
                            )
            );
        }else {
            if (command.getTitle().isEmpty()) {
                response.getErrorMessage().put("IsEmptyTitleFieldError", "this Field shouldn't be empty");
                response.setValid(false);
            }
            if (command.getRequiredTime() < 5 || command.getRequiredTime() > 240) {
                response.getErrorMessage().put("NotLogicalRequiredTimeError", "Required Time shouldn't less than 5 min and more than 240");
                response.setValid(false);
            }
            examRepository.findById(command.getExamId()).ifPresent(
                    exam -> {
                        if (exam.getAccount().getId().equals(masterId)) {
                            //edit property
                            if(response.isValid()) {
                                exam.setTimeRequired(command.getRequiredTime());
                                exam.setTitle(command.getTitle());
                                exam.setDetails(command.getDetails());
                                examRepository.saveAndFlush(exam);
                            }
                        }else {
                            response.setValid(false);
                            response.getErrorMessage().put("NotExistThisTestForMaster",
                                    "This Test is not yours.");

                        }

                    }
            );
        }
        return response;
    }
}
