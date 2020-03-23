package ir.management.onlinetest.features.course_management.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class ListCourseByMasterCommand {
    private List<String> searchAttribute;
    private int pageNo;
    private int pageSize;
}