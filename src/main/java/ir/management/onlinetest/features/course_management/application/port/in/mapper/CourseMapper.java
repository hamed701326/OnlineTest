package ir.management.onlinetest.features.course_management.application.port.in.mapper;

import ir.management.onlinetest.core.annotation.Mapper;
import ir.management.onlinetest.entities.Course;
import ir.management.onlinetest.features.course_management.application.port.out.CourseDTO;

@Mapper
public class CourseMapper {
    public static CourseDTO map(Course course){
        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getStartDate(),
                course.getExpiredDate()
        );
    }
}
