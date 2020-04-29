package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import ir.management.onlinetest.core.annotation.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@Command
public class StartExamByMasterCommand {
    private Long examId;
}
