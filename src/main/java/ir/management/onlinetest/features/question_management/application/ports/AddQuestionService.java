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
                        Question question;
                        if (command.getType().equals("Choice Question")) {
                            question=new ChoiceQuestion(
                                    command.getAnswers(),
                                    command.getTitle(),
                                    command.getContent(),
                                    command.getLevel(),
                                    command.getScore()
                            );
                            exam.getQuestions()
                                    .put(
                                            questionRepository.saveAndFlush(question)
                                            ,
                                            command.getScore()
                                    );
                            exam.setNumberOfQuestion(
                                    exam.getNumberOfQuestion()+1
                            );

                        } else {
                            question=new FreeResponseQuestion(
                                    command.getTitle(),
                                    command.getContent(),
                                    command.getAnswer(),
                                    command.getLevel(),
                                    command.getScore()
                            );

                            exam.getQuestions()
                                    .put(
                                           questionRepository.saveAndFlush(question)
                                            ,
                                            command.getScore()
                                    );
                            exam.setNumberOfQuestion(
                                    exam.getNumberOfQuestion()+1
                            );
                        }
                        examRepository.save(exam);
                        examRepository.flush();
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
            response.setErrorMessages(
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
                                //if exam doesn't have this question:
                                if(!exam.getQuestions().containsKey(question)) {
                                    if (command.isEdited()) {

                                        exam.getQuestions().put(
                                                question, command.getScore()
                                        );
                                    } else {
                                        // adding question to exam
                                        exam.getQuestions().put(
                                                question, question.getScore()
                                        );
                                        exam.setNumberOfQuestion(
                                                exam.getNumberOfQuestion() + 1
                                        );
                                    }
                                    // saving exam and flush:
                                    examRepository.save(exam);
                                    examRepository.flush();
                                    //set response:
                                    response.setValid(true);
                                }else{
                                    response.getErrorMessages().put(
                                            "ExistedQuestionInExam",
                                            "This question added to this exam before."
                                    );
                                }


                            }
                            ,
                            //if exam not exist
                            () -> {
                                response.getErrorMessages().put(
                                        "NotExistedExamWithId","There isn't any exam with this Id."
                                );
                            }
                    );
                },
                // if question doesn't exist
                ()->{
                    response.getErrorMessages().put(
                      "NotExistedQuestionWithId","There isn't any question with Id."
                    );
                }
            );
        }

        return response;
    }
}
