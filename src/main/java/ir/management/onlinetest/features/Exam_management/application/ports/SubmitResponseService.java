package ir.management.onlinetest.features.Exam_management.application.ports;

import ir.management.onlinetest.entities.take_exam.TakeAnswer;
import ir.management.onlinetest.features.Exam_management.application.ports.in.SubmitResponseByStudentUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.SubmitResponseByStudentCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.SubmitResponseByStudentOutcome;
import ir.management.onlinetest.repositories.AnswerRepository;
import ir.management.onlinetest.repositories.QuestionRepository;
import ir.management.onlinetest.repositories.TakeAnswerRepository;
import ir.management.onlinetest.repositories.TakeExamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class SubmitResponseService implements SubmitResponseByStudentUseCase {
    private final TakeExamRepository takeExamRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final TakeAnswerRepository takeAnswerRepository;
    public SubmitResponseService(TakeExamRepository takeExamRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, TakeAnswerRepository takeAnswerRepository) {
        this.takeExamRepository = takeExamRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.takeAnswerRepository = takeAnswerRepository;
    }

    @Override
    public SubmitResponseByStudentOutcome submitByStudent(SubmitResponseByStudentCommand command,
                                                          HttpServletRequest request,
                                                          BindingResult result) {
        SubmitResponseByStudentOutcome response=new SubmitResponseByStudentOutcome();
        response.setValid(false);
        if(result.hasErrors()){
            response.getErrorMessages().putAll(
                    result
                            .getFieldErrors()
                            .stream()
                            .collect(Collectors
                                    .toMap(FieldError::getField, FieldError::getDefaultMessage)
                            )
            );
        }else{
            Long takeExamId= (Long) request.getSession().getAttribute("takeExamId");
            if(!(takeExamId ==null)){
                takeExamRepository.findById(takeExamId)
                        .ifPresentOrElse(
                                takeExam -> {

                                    questionRepository.findById(command.getQuestionId()).ifPresentOrElse(
                                            question -> {
                                                answerRepository.findById(command.getAnswerId()).ifPresentOrElse(
                                                        answer -> {
                                                            TakeAnswer takeAnswer=takeAnswerRepository.findByAnswerAndTakeExam(answer,takeExam);
                                                            if(takeAnswer==null) {
                                                                takeAnswer=new TakeAnswer();
                                                                takeAnswer.setQuestion(question);
                                                                takeAnswer.setMainScore(question.getScore());
                                                                takeAnswer.setAnswer(answer);
                                                                takeAnswer.setActive(true);
                                                                takeAnswer.setTakeExam(takeExam);
                                                                takeAnswer.setUpdateAt(new Date());
                                                                takeAnswer.setCreateAt(new Date());
                                                            }else {
                                                                takeAnswer.setAnswer(answer);
                                                                takeAnswer.setUpdateAt(new Date());
                                                            }
                                                            takeAnswerRepository.saveAndFlush(takeAnswer);
                                                            response.setValid(true);
                                                        },
                                                        ()->{
                                                            response.getErrorMessages().put(
                                                                    "NotExistAnswerWithThisId","There isn't any answer with answerId"
                                                            );

                                                        }
                                                );
                                            }
                                            ,
                                            ()->{
                                                response.getErrorMessages()
                                                        .put("NotExistQuestionWithThisId","There isn't any question with questionId");
                                            }
                                    );



                                }
                                ,
                                ()->{
                                    response.getErrorMessages()
                                            .put("NotExistTakeExamWithThisId","There isn't any takeExam with id");
                                }
                        );
            }else {
                response.getErrorMessages()
                        .put("NOTExistExamId","there isn't one id for this exam in http session");
            }
        }
        return response;
    }
}
