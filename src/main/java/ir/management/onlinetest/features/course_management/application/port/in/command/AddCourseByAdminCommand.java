package ir.management.onlinetest.features.course_management.application.port.in.command;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class AddCourseByAdminCommand {
    private String title;
    private Date startDate;
    private Date expireDate;
}
