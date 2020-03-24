package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditExamByMasterCommand {
    private Long examId;
    private String title;
    private String details;
    private double requiredTime;
}
