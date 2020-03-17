package ir.management.onlinetest.features.course_management.application.port.in.outcome;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class AddCourseByAdminOutcome {
    Long courseId;
    boolean validated;
    Map<String,String> errorMessages;
}
