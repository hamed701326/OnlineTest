package ir.management.onlinetest.features.question_management.application.ports;

import ir.management.onlinetest.entities.question.Question;
import ir.management.onlinetest.features.question_management.application.ports.in.FindQuestionByMasterUseCase;
import ir.management.onlinetest.features.question_management.application.ports.in.ListAllQuestionByMasterUseCase;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.FindQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.ListAllQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.FindQuestionByMasterOutcome;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.ListAllQuestionByMasterOutcome;
import ir.management.onlinetest.features.question_management.domain.QuestionDTO;
import ir.management.onlinetest.repositories.ExamRepository;
import ir.management.onlinetest.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FindQuestionService implements FindQuestionByMasterUseCase, ListAllQuestionByMasterUseCase {
    private final QuestionRepository questionRepository;
    private final ExamRepository examRepository;
    public FindQuestionService(QuestionRepository questionRepository, ExamRepository examRepository) {
        this.questionRepository = questionRepository;
        this.examRepository = examRepository;
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
            //todo: write code in better way, this doesn't work
            List<Question> questions=questionRepository
                    .findAllByCourseId(command.getCourseId(),command.getTitle());
            if (!questions.isEmpty())
                response.setQuestions(
                    questions
                );
            else
                response.setQuestions(new ArrayList<>());
        }
        return response;
    }

    @Override
    public ListAllQuestionByMasterOutcome listByMaster(ListAllQuestionByMasterCommand command, HttpServletRequest request, BindingResult result) {
        ListAllQuestionByMasterOutcome response=new ListAllQuestionByMasterOutcome();
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
        }else {
            response.setValid(true);
            Map<Question, Float> questionFloatMap = examRepository
                    .findById(command.getExamId())
                    .get()
                    .getQuestions();
            if (!questionFloatMap.isEmpty()) {
                questionFloatMap
                        .keySet()
                        .forEach(
                                question ->
                                        question
                                                .setScore(
                                                        questionFloatMap
                                                                .getOrDefault(question, question.getScore())
                                                )
                        );

                response.setQuestions(
                        questionFloatMap
                                .keySet()
                                .stream()
                                .map(QuestionDTO::new)
                                .collect(Collectors.toList())
                );
            }
            else
                response.setQuestions(new ArrayList<>());
        }
        return response;
    }
}
