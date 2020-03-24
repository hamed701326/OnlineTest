package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteExamByMasterCommand {
    private Long examId;
}
