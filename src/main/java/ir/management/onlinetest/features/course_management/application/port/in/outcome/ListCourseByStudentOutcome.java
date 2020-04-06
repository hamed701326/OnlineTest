package ir.management.onlinetest.features.course_management.application.port.in.outcome;

import ir.management.onlinetest.features.course_management.application.port.out.CourseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ListCourseByStudentOutcome {
    List<CourseDTO> courseList;
    boolean validated;
    private Map<String, String> errorMessages;
}
