package ir.management.onlinetest.features.course_management.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class CourseDTO {
    private Long id;
    private String name;
    private Date startDate;
    private Date expiredDate;

}
