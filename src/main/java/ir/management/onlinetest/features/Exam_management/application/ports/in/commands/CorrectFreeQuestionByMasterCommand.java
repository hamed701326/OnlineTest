package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorrectFreeQuestionByMasterCommand {
    private Long takeExamId;
    private Long takeAnswerId;
    private Float score;
}
