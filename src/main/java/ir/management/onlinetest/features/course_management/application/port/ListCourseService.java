package ir.management.onlinetest.features.course_management.application.port;

import ir.management.onlinetest.entities.Course;
import ir.management.onlinetest.features.course_management.application.port.in.ListCourseByAdminUseCase;
import ir.management.onlinetest.features.course_management.application.port.in.command.ListCourseByAdminCommand;
import ir.management.onlinetest.features.course_management.application.port.in.mapper.CourseMapper;
import ir.management.onlinetest.features.course_management.application.port.in.outcome.ListCourseByAdminOutcome;
import ir.management.onlinetest.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListCourseService implements ListCourseByAdminUseCase {
    @Autowired
    CourseRepository courseRepository;
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
}
