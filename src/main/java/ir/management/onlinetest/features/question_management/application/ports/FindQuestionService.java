package ir.management.onlinetest.features.question_management.application.ports;

import ir.management.onlinetest.entities.question.Question;
import ir.management.onlinetest.features.question_management.application.ports.in.FindQuestionByMasterUseCase;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.FindQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.FindQuestionByMasterOutcome;
import ir.management.onlinetest.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FindQuestionService implements FindQuestionByMasterUseCase {
    private final QuestionRepository questionRepository;

    public FindQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public FindQuestionByMasterOutcome FindByMaster(FindQuestionByMasterCommand command,
                                                    BindingResult result,
                                                    HttpServletRequest request) {
        FindQuestionByMasterOutcome response=new FindQuestionByMasterOutcome();

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
            response.setValid(true);
            Map<Question,Float> questionFloatMap=questionRepository.findAllByCourseId(command.getCourseId());
            if (!questionFloatMap.isEmpty())
                response.setQuestions(
                    questionFloatMap
                        .keySet()
                        .stream()
                        .filter(
                                question -> question.getTitle().contains(command.getTitle())
                        )
                        .collect(Collectors.toList())
                );
            else
                response.setQuestions(new ArrayList<>());
        }
        return response;
    }
}
