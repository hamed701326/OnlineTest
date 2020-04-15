package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import ir.management.onlinetest.core.annotation.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter@Getter
@AllArgsConstructor
@Command
public class StartExamByStudentCommand {
    private Long ExamId;

}
