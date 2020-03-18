package ir.management.onlinetest.features.course_management.application.port;

import ir.management.onlinetest.entities.Course;
import ir.management.onlinetest.features.course_management.application.port.in.AddCourseByAdminUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.command.AddCourseByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.AddCourseByAdminOutcome;
import ir.management.onlinetest.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AddCourseService implements AddCourseByAdminUseCase {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public AddCourseByAdminOutcome addCourse(AddCourseByAdminCommand command, BindingResult result) {
        Optional<Course> course =Optional.of(courseRepository.saveAndFlush(new Course(
                null,
                command.getTitle(),
                new Date(),
                command.getStartDate(),
                command.getExpiredDate(),
                null
                )
        ));
        AddCourseByAdminOutcome response=new AddCourseByAdminOutcome();
        if(result.hasErrors()){

            //Get error message
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            response.setValidated(false);
            response.setErrorMessages(errors);
        }else{
            // Implement business logic to save employee into database
            //..
            response.setValidated(true);
            response.setCourseId(course.orElse(new Course()).getId());

        }
        return response;
    }
}
