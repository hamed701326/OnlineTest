package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import ir.management.onlinetest.core.annotation.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
@Command
public class FinishExamByMasterCommand {
    private Long examId;
}
