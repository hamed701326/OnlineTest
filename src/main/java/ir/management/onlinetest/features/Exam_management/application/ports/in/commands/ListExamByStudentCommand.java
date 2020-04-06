package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import ir.management.onlinetest.core.annotation.Command;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Command
public class ListExamByStudentCommand {
    private List<String> searchAttribute;
    private int pageNo;
    private int pageSize;
    private  Long courseId;
}
