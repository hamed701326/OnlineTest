package ir.management.onlinetest.features.Exam_management.application.ports;

import ir.management.onlinetest.features.Exam_management.application.ports.in.ListExamByMasterUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.ListExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.ListExamByMasterOutcome;
import ir.management.onlinetest.features.Exam_management.domain.ExamDTO;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.ExamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
@Service
public class ListExamService implements ListExamByMasterUseCase {
    private final AccountRepository accountRepository;
    private final ExamRepository examRepository;
    public ListExamService(AccountRepository accountRepository, ExamRepository examRepository){
        this.accountRepository=accountRepository;
        this.examRepository = examRepository;
    }
    @Override
    public ListExamByMasterOutcome ListByMaster(ListExamByMasterCommand command,
                                                HttpServletRequest request,
                                                BindingResult result) {
        ListExamByMasterOutcome response=new ListExamByMasterOutcome();
         if(result.hasErrors())
         {
             response.setValidated(false);
             response.setErrorMessage(result
                     .getFieldErrors()
                     .stream()
                     .collect(Collectors
                             .toMap(FieldError::getField, FieldError::getDefaultMessage)
                     )
             );
         }else {
            response.setExamList(
             examRepository
                     .findAllByCourseIdAndAccountId(
                             command.getCourseId(),
                             (Long) request.getSession().getAttribute("accountId")
                     )
                     .stream()
                     .map(ExamDTO::new)
                     .collect(Collectors.toList())
            );
            response.setValidated(true);
         }
         return response;
    }
}
