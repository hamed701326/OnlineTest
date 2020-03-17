package ir.management.onlinetest.features.course_management.application.port.in.outcome;

import ir.management.onlinetest.entities.Course;
import ir.management.onlinetest.features.course_management.application.port.out.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
@AllArgsConstructor
@Getter
public class ListCourseByAdminOutcome {
    List<CourseDTO> courseList;
}
