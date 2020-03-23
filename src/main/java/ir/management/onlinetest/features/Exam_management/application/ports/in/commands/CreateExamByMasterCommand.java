package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateExamByMasterCommand {
    private Long courseId;
    private String title;
    private String details;
    private double requiredTime;
}
