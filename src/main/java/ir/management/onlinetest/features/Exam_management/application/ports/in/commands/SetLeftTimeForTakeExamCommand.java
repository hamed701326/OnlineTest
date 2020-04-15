package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor
public class SetLeftTimeForTakeExamCommand {
    private double leftTime;
}
