package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TakeExamByStudentCommand {
    private Long examId;
}
