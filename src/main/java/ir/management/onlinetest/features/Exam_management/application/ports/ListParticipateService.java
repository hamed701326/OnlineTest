package ir.management.onlinetest.features.Exam_management.application.ports;

import ir.management.onlinetest.enums.StatusExam;
import ir.management.onlinetest.features.Exam_management.application.ports.in.ListParticipateInExamByMasterUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.ListParticipateInExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.ListParticipateInExamByMasterOutcome;
import ir.management.onlinetest.features.Exam_management.domain.Participator;
import ir.management.onlinetest.repositories.ExamRepository;
import ir.management.onlinetest.repositories.TakeExamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
@Service
public class ListParticipateService implements ListParticipateInExamByMasterUseCase {
    private final ExamRepository examRepository;
    private final TakeExamRepository takeExamRepository;

    public ListParticipateService(ExamRepository examRepository,
                                  TakeExamRepository takeExamRepository) {
        this.examRepository = examRepository;
        this.takeExamRepository = takeExamRepository;
    }


    @Override
    public ListParticipateInExamByMasterOutcome listByMaster(ListParticipateInExamByMasterCommand command,
                                                             HttpServletRequest request,
                                                             BindingResult result) {
        ListParticipateInExamByMasterOutcome response=new ListParticipateInExamByMasterOutcome();
        response.setValid(false);
        String feature="in ListParticipateInExamByMaster: ";
        if(result.hasErrors()){
            response.setErrorMessages(result
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors
                            .toMap(FieldError::getField, FieldError::getDefaultMessage)
                    )
            );
        }else {
            Long masterId= (Long) request.getSession().getAttribute("accountId");
            if(masterId!=null) {
                examRepository
                        .findByCourseIdAndAccountIdAndStatus(
                                command.getCourseId(),
                                masterId,
                                StatusExam.Ready)
                        .ifPresentOrElse(
                                exam -> {
                                    response.setParticipateList(
                                            takeExamRepository
                                                    .findAllByExam(exam)
                                                    .stream()
                                                    .map(Participator::new)
                                                    .collect(Collectors.toList())
                                    );
                                    response.setValid(true);
                                },
                                () -> {
                                    response.getErrorMessages()
                                            .put("NotExistExam", feature + "There isn't a exam with these courseId and AccountId.");
                                }
                        );
            }else
            {
                response.getErrorMessages()
                        .put("NotExistAccountIdInSession",feature+"there isn't a attribute with name accountId in HttpSession.");
            }

        }

        return response;
        }
}
