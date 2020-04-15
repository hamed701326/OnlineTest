package ir.management.onlinetest.features.Exam_management.application.ports;

import ir.management.onlinetest.entities.Exam;
import ir.management.onlinetest.entities.question.ChoiceQuestion;
import ir.management.onlinetest.entities.question.Question;
import ir.management.onlinetest.entities.take_exam.TakeExam;
import ir.management.onlinetest.enums.StatusExam;
import ir.management.onlinetest.features.Exam_management.application.ports.in.FinishExamByStudentUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.SetLeftTimeForTakeExamUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.TakeExamByStudentUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.SetLeftTimeForTakeExamCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.TakeExamByStudentCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.FinishExamByStudentOutcome;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.SetLeftTimeForTakeExamOutcome;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.TakeExamByStudentOutcome;
import ir.management.onlinetest.features.question_management.domain.QuestionDTO;
import ir.management.onlinetest.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TakeExamService implements TakeExamByStudentUseCase, SetLeftTimeForTakeExamUseCase, FinishExamByStudentUseCase {

    private final ExamRepository examRepository;
    private final TakeExamRepository takeExamRepository;
    private final AccountRepository accountRepository;
    private final TakeAnswerRepository takeAnswerRepository;
    public TakeExamService(ExamRepository examRepository,
                           TakeExamRepository takeExamRepository,
                           AccountRepository accountRepository,
                           TakeAnswerRepository takeAnswerRepository) {
        this.examRepository = examRepository;
        this.takeExamRepository = takeExamRepository;
        this.accountRepository = accountRepository;
        this.takeAnswerRepository = takeAnswerRepository;
    }

    @Override
    public TakeExamByStudentOutcome takeByStudent(TakeExamByStudentCommand command, HttpServletRequest request, BindingResult result) {
        TakeExamByStudentOutcome response=new TakeExamByStudentOutcome();
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
//            Map<Question,Float> questions=questionRepository
//                    .findAllByExamId(command.getExamId());
            Map<Question,Float> questions=new HashMap<>();
            Optional<Exam> exam=examRepository
                    .findById(command.getExamId());
            exam.ifPresentOrElse(
                            exam1 -> {
                                questions.putAll(
                                    exam1.getQuestions()
                                );
                                response.setTime(exam1.getTimeRequired());
                            }
                            ,
                            ()->{}
                    );
            if(questions.isEmpty()){
                response.getErrorMessages()
                        .put(
                            "NotExistQuestionForThisExam","The Question not created now"
                        );
            }
            else{
                //setting new score
                questions.keySet()
                        .stream()
                        .forEach(q ->
                                q.setScore(
                                        questions.getOrDefault(q, q.getScore()))
                        );
                //setting list of question
                response.setQuestions(
                        questions.keySet()
                        .stream()
                        .map(QuestionDTO::new).collect(Collectors.toList())
                );

                //create TakeExam:
                exam.ifPresent(
                        exam1 -> {
                            accountRepository
                                    .findById(
                                            (Long) request
                                                    .getSession()
                                                    .getAttribute("accountId")
                                    )
                                    .ifPresent(
                                    account1 ->
                                    {
                                        takeExamRepository.findByExamAndAccount(exam1,account1)
                                                .ifPresentOrElse(
                                                        takeExam ->
                                                        {
                                                            if(takeExam.getStatus()== StatusExam.Doing) {
                                                                request.getSession().setAttribute(
                                                                        "takeExamId",
                                                                        takeExam.getId()
                                                                );
                                                                //ToDo: How to set exam
                                                                response.setTime(takeExam.getLeftTime());
                                                                response.setValid(true);
                                                            }else if(takeExam.getStatus()==StatusExam.Finished)
                                                                response.getErrorMessages()
                                                                        .put(
                                                                                "ExamFinished",
                                                                                "Your time is over"
                                                                        );
                                                        },
                                                        () ->
                                                        {
                                                            TakeExam takeExam=new TakeExam();
                                                            takeExam.setAccount(account1);
                                                            takeExam.setExam(exam1);
                                                            takeExam.setMaxScore(exam1.getScore());
                                                            takeExam.setCreateAt(new Date());
                                                            takeExam.setStartedAt(new Date());
                                                            takeExam.setStatus(StatusExam.Doing);
                                                            takeExam.setLeftTime(exam1.getTimeRequired());
                                                            request.getSession().setAttribute(
                                                                    "takeExamId",
                                                                    takeExamRepository.saveAndFlush(takeExam).getId()
                                                            );

                                                            response.setValid(true);


                                                        }
                                                );
                                    }
                            );

                        }
                );

            }
        }
            return response;
    }

    @Override
    public SetLeftTimeForTakeExamOutcome setLeftTime(SetLeftTimeForTakeExamCommand command,
                                                     BindingResult result,
                                                     HttpServletRequest request) {
        SetLeftTimeForTakeExamOutcome response=new SetLeftTimeForTakeExamOutcome();
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
            Long takeExamId= (Long) request.getSession().getAttribute("takeExamId");
            String feature="SetLeftTimeForTakeExam: ";
            if(takeExamId==null){
                response.getErrorMessages().put(
                        "NotExistTakeExamIdAttributeInSession",feature+"There isn't any attribute  with name " +
                                "'takeExamId' in HttpSession"
                );
            }
            else {
                takeExamRepository.findById(takeExamId).ifPresentOrElse(
                        takeExam -> {
                            takeExam.setLeftTime(command.getLeftTime());
                            takeExamRepository.saveAndFlush(takeExam);
                            response.setValid(true);
                        }
                        ,
                        () -> {
                            response.getErrorMessages()
                                    .put(
                                            "NotExistedTakeExamWithId", feature + "There isn't any takeExam with id."
                                    );
                        }
                );
            }

        }

        return response;
    }

    @Override
    public FinishExamByStudentOutcome determineScoreAutomatically(HttpServletRequest request,BindingResult result) {
        FinishExamByStudentOutcome response=new FinishExamByStudentOutcome();
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
            Long takeExamId= (Long) request.getSession().getAttribute("takeExamId");
            String feature="FinishExamByStudent: ";
            if(takeExamId==null){
                response.getErrorMessages().put(
                        "NotExistTakeExamIdAttributeInSession",feature+"There isn't any attribute  with name " +
                                "'takeExamId' in HttpSession"
                );
            }
            else {
                float score=0;
                takeExamRepository.findById(takeExamId).ifPresentOrElse(

                        takeExam -> {
                            //determine score automatically:
                            takeAnswerRepository.findAllByTakeExam(takeExam)
                                    .stream()
                                    .filter(
                                            takeAnswer -> takeAnswer.getAnswer().isCorrect() && (takeAnswer.getQuestion() instanceof ChoiceQuestion)
                                            )
                                    .forEach(
                                            takeAnswer ->takeExam.setScore(takeExam.getScore()+takeAnswer.getScore())
                                    );
                            takeExamRepository.saveAndFlush(takeExam);
                            response.setValid(true);
                            response.setScore(takeExam.getScore());
                            //removing takeExamId Attribute in HttpSession:
                            request.getSession().removeAttribute("takeExamId");
                        }
                        ,
                        () -> {
                            response.getErrorMessages()
                                    .put(
                                            "NotExistedTakeExamWithId", feature + "There isn't any takeExam with id."
                                    );
                        }
                );
            }
        }

        return response;
    }
}
