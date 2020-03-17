package ir.management.onlinetest.features.course_management.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListCourseByAdminCommand {
    private List<String> searchAttribute;
    private int pageNo;
    private int pageSize;
}
