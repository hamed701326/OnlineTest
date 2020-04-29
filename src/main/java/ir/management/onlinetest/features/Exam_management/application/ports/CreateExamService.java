package ir.management.onlinetest.features.Exam_management.application.ports;

import ir.management.onlinetest.entities.Exam;
import ir.management.onlinetest.enums.StatusExam;
import ir.management.onlinetest.features.Exam_management.application.ports.in.CreateExamByMasterUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.FinishExamByMasterUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.StartExamByMasterUseCase;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.CreateExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.FinishExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.commands.StartExamByMasterCommand;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.CreateExamByMasterOutcome;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.FinishExamByMasterOutcome;
import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.StartExamByMasterOutcome;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.CourseRepository;
import ir.management.onlinetest.repositories.ExamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class CreateExamService implements CreateExamByMasterUseCase, StartExamByMasterUseCase, FinishExamByMasterUseCase {
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
            response.setErrorMessages(
                    getCollect(result)
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
                            StatusExam.UnReady,
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


    private Map<String, String> getCollect(BindingResult result) {
        return result
                .getFieldErrors()
                .stream()
                .collect(Collectors
                        .toMap(FieldError::getField, FieldError::getDefaultMessage)
                );
    }

    @Override
    public StartExamByMasterOutcome startByMaster(StartExamByMasterCommand command, HttpServletRequest request, BindingResult result) {
        StartExamByMasterOutcome response=new StartExamByMasterOutcome();
        response.setValid(false);
        String feature="in StartExamByMaster:";
        if(result.hasErrors())
        {
            response.setErrorMessages(
                    getCollect(result)
            );
        }else {
            examRepository
                    .findById(command.getExamId())
                    .ifPresentOrElse(
                            exam -> {
                                if(exam.getStatus()!=StatusExam.Finished) {
                                    if(exam.getStatus()==StatusExam.UnReady) {
                                        exam.setStatus(StatusExam.Ready);
                                        examRepository.saveAndFlush(exam);
                                        response.setValid(true);
                                    }else {
                                        response.getErrorMessages()
                                                .put(
                                                        "ExamStartedBefore",feature+"Exam can't begin again."
                                                );
                                    }
                                }else {
                                    response.getErrorMessages()
                                            .put(
                                                    "ExamFinished",feature+"Exam can't  begin because it's finished."
                                            );
                                }
                            },
                            ()->{
                                response.getErrorMessages()
                                        .put(
                                                "NotExitExam",feature+"There isn't a exam with id in database"
                                        );
                            }
                    );
        }
        return response;

    }

    @Override
    public FinishExamByMasterOutcome finishByMaster(FinishExamByMasterCommand command, HttpServletRequest request, BindingResult result) {
        FinishExamByMasterOutcome response=new FinishExamByMasterOutcome();
        String feature="in FinishExamByMaster";
        if(result.hasErrors())
        {
            response.setErrorMessages(
                    getCollect(result)
            );
        }else {
            examRepository
                    .findById(command.getExamId())
                    .ifPresentOrElse(
                            exam -> {
                                if(exam.getStatus()!=StatusExam.Finished) {
                                    if(exam.getStatus()!=StatusExam.UnReady) {
                                        exam.setStatus(StatusExam.Finished);
                                        examRepository.saveAndFlush(exam);
                                        response.setValid(true);
                                    }else {
                                        response.getErrorMessages()
                                                .put(
                                                        "ExamDidn'tStart",feature+"Exam Still didn't begin ."
                                                );
                                    }
                                }else {
                                    response.getErrorMessages()
                                            .put(
                                                    "ExamFinished",feature+"Exam can't  finished again."
                                            );
                                }
                            },
                            ()->{
                                response.getErrorMessages()
                                        .put(
                                                "NotExitExam",feature+"There isn't a exam with id in database"
                                        );
                            }
                    );
        }
        return response;
    }
}
