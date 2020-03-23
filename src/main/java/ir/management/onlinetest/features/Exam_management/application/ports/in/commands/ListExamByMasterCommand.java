package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ListExamByMasterCommand {
    private List<String> searchAttribute;
    private int pageNo;
    private int pageSize;
    private  Long courseId;

}
