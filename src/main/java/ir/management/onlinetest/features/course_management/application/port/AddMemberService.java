package ir.management.onlinetest.features.course_management.application.port;

import ir.management.onlinetest.entities.Course;
import ir.management.onlinetest.features.course_management.application.port.in.AddMemberByAdminUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.command.AddMemberByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.AddCourseByAdminOutcome;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.AddMemberByAdminOutcome;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AddMemberService implements AddMemberByAdminUseCase {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public AddMemberByAdminOutcome addMember(AddMemberByAdminCommand command, BindingResult result) {
        AddMemberByAdminOutcome response=new AddMemberByAdminOutcome();
        Map<String, String> errors;
        if(result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );
            response.setValidated(false);
            response.setErrorMessage(errors);

        }else {
            accountRepository.findById(command.getUserId()).ifPresentOrElse(
                    account ->
                        courseRepository.findById(command.getCourseId()).ifPresentOrElse(
                                // if there is any course with this id
                                course -> {
                                    course.getMembers().add(account);
                                    courseRepository.saveAndFlush(course);
                                    response.setValidated(true);
                                }
                                ,
                                // if there isn't
                                () -> {
                                    response.setValidated(false);
                                    response.setErrorMessage(
                                            Map.of(
                                            "NotFoundEntity",
                                            "Entity for "+ Course.class.getName()+" with this Id ="+command.getCourseId()+" doesn't exist."
                                            )
                                    );
                                }
                        )
                    ,
                    ()->
                    {
                        response.setValidated(false);
                        response.setErrorMessage(
                                Map.of(
                                        "NotFoundEntity",
                                "Entity for "+ Course.class.getName()+" with this Id ="+command.getCourseId()+" doesn't exist."
                                )
                        );
                    }
            );
        }
        return response;
    }
}
