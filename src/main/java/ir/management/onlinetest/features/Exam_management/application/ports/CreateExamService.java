package ir.management.onlinetest.features.Exam_management.application.ports;

import ir.management.onlinetest.entities.Exam;
import ir.management.onlinetest.features.Exam_management.application.ports.in.CreateExamByMasterUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.CreateExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.CreateExamByMasterOutcome;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.CourseRepository;
import ir.management.onlinetest.repositories.ExamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
@Service
public class CreateExamService implements CreateExamByMasterUseCase {
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final AccountRepository accountRepository;
    public CreateExamService(ExamRepository examRepository, CourseRepository courseRepository, AccountRepository accountRepository){
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
        this.accountRepository = accountRepository;
    }
    @Override
    public CreateExamByMasterOutcome createByMaster(CreateExamByMasterCommand command,
                                                    HttpServletRequest request,
                                                    BindingResult result) {
        CreateExamByMasterOutcome response=new CreateExamByMasterOutcome();
        if(result.hasErrors()){
            response.setValidated(false);
            response.setErrorMessage(
                    result
                            .getFieldErrors()
                            .stream()
                            .collect(Collectors
                                    .toMap(FieldError::getField, FieldError::getDefaultMessage)
                            )
            );
        }else {
            examRepository.saveAndFlush(
                    new Exam(
                           null,
                            command.getTitle(),
                            command.getDetails(),
                            null,
                            command.getRequiredTime(),
                            0,
                            new Date(),
                            courseRepository.findById(command.getCourseId()).get(),
                            accountRepository.findById((Long) request.getSession().getAttribute("accountId")).get(),
                            new HashMap<>()
                    )
            );
           response.setValidated(true);
        }
        return response;

    }
}
