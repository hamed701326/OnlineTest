package ir.management.onlinetest.features.question_management.application.ports;

import ir.management.onlinetest.entities.question.ChoiceQuestion;
import ir.management.onlinetest.entities.question.FreeResponseQuestion;
import ir.management.onlinetest.entities.question.Question;
import ir.management.onlinetest.enums.Level;
import ir.management.onlinetest.features.question_management.application.ports.in.AddQuestionByMasterUseCase;
import ir.management.onlinetest.features.question_management.application.ports.in.UseQuestionByMasterUseCase;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.AddQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.commands.UseQuestionByMasterCommand;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.AddQuestionByMasterOutcome;
import ir.management.onlinetest.features.question_management.application.ports.in.outcomes.UseQuestionByMasterOutcome;
import ir.management.onlinetest.repositories.ExamRepository;
import ir.management.onlinetest.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
@Service
public class AddQuestionService implements AddQuestionByMasterUseCase, UseQuestionByMasterUseCase {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;

    public AddQuestionService(ExamRepository examRepository, QuestionRepository questionRepository) {
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public AddQuestionByMasterOutcome addQuestionByMaster(AddQuestionByMasterCommand command,
                                                          BindingResult result,
                                                          HttpServletRequest request) {
        AddQuestionByMasterOutcome response=new AddQuestionByMasterOutcome();
        response.setValid(false);
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
            examRepository.findById(command.getExamId()).ifPresentOrElse(
                    exam -> {
                        response.setValid(true);
                        if (command.getType().equals("choice")) {
                            exam.getQuestions()
                                    .put(
                                            new ChoiceQuestion(
                                                    command.getAnswers(),
                                                    command.getTitle(),
                                                    command.getContent(),
                                                    command.getLevel(),
                                                    command.getScore()
                                            )
                                            ,
                                            command.getScore()
                                    );

                        } else {
                            exam.getQuestions()
                                    .put(
                                            new FreeResponseQuestion(
                                                    command.getTitle(),
                                                    command.getContent(),
                                                    command.getAnswer(),
                                                    command.getLevel(),
                                                    command.getScore()
                                            )
                                            ,
                                            command.getScore()
                                    );

                        }
                        examRepository.saveAndFlush(exam);
                    }
                    ,
                    ()->{
                        response.getErrorMessage().put("NotExistExam","there aren't any exam with this id.");
                    }
            );
        }
        return response;
    }


    @Override
    public UseQuestionByMasterOutcome useFromQuestionBank(UseQuestionByMasterCommand command, BindingResult result, HttpServletRequest request) {
        UseQuestionByMasterOutcome response=new UseQuestionByMasterOutcome();
        response.setValid(false);
        if(result.hasErrors()){
            response.setErrorMessage(
                    result
                            .getFieldErrors()
                            .stream()
                            .collect(Collectors
                                    .toMap(FieldError::getField, FieldError::getDefaultMessage)
                            )
            );
        }else {
            questionRepository.findById(command.getQuestionId()).ifPresentOrElse(
                //if question exist
                question -> {
                    examRepository.findById(command.getExamId()).ifPresentOrElse(
                            // if exam exist:
                            exam -> {
                                // adding question to exam
                                exam.getQuestions().put(
                                        question,question.getScore()
                                );
                                // saving exam and flush:
                                examRepository.saveAndFlush(exam);
                                //set response:
                                response.setValid(true);
                            }
                            ,
                            //if exam not exist
                            () -> {
                                response.getErrorMessage().put(
                                        "NotExistedExamWithId","There isn't any exam with this Id."
                                );
                            }
                    );
                },
                // if question doesn't exist
                ()->{
                    response.getErrorMessage().put(
                      "NotExistedQuestionWithId","There isn't any question with Id."
                    );
                }
            );
        }

        return response;
    }
}
