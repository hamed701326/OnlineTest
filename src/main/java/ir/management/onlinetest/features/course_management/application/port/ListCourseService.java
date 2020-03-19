package ir.management.onlinetest.features.course_management.application.port;

import ir.management.onlinetest.entities.Course;
import ir.management.onlinetest.features.course_management.application.port.in.ListCourseByAdminUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.ListCourseByMasterUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.command.ListCourseByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.command.ListCourseByMasterCommand;
import ir.management.onlinetest.features.course_management.application.port.in.mapper.CourseMapper;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListCourseByAdminOutcome;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListCourseByMasterOutcome;
import ir.management.onlinetest.repositories.AccountRepository;
import ir.management.onlinetest.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ListCourseService implements ListCourseByAdminUseCase , ListCourseByMasterUseCase {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    AccountRepository accountRepository;
    @Override
    public ListCourseByAdminOutcome list(ListCourseByAdminCommand command) {
        //Todo command is setted
        Pageable paging = PageRequest.of(1, 10);
        List<Course> courseList=null;
        command.setSearchAttribute(new ArrayList<>());
        if((command.getSearchAttribute()).size()==0)
            courseList=courseRepository.findAll();


        return
                courseList!=null ?
                        new ListCourseByAdminOutcome(
                courseList
                .stream()
                .map(CourseMapper::map).collect(Collectors.toList())):null;
    }

    @Override
    public ListCourseByMasterOutcome listByMaster(ListCourseByMasterCommand command, BindingResult result) {
        ListCourseByMasterOutcome response=new ListCourseByMasterOutcome();
        Pageable paging = PageRequest.of(1, 10);

        Map<String, String> errors;

        if(result.hasErrors()){

            //Get error message
            errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            response.setValidated(false);
            response.setErrorMessages(errors);
        }else {
            //Todo: check this user exist in database
            accountRepository.findById(command.getMasterId()).ifPresentOrElse(
             account ->        {
                    List<Course> courseList = null;
                    command.setSearchAttribute(new ArrayList<>());
                    if ((command.getSearchAttribute()).size() == 0)
                     courseList = account.getCourseList();

                    response.setCourseList(
                            courseList != null ?
                                    courseList
                                            .stream()
                                            .map(CourseMapper::map).collect(Collectors.toList())
                                    :
                                    null
                    );
            },
            ()->{}
            );
        }
          return response;
    }
}
