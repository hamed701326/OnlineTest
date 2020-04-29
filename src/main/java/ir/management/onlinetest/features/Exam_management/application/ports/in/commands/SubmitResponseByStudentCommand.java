package ir.management.onlinetest.features.Exam_management.application.ports.in.commands;

import ir.management.onlinetest.core.annotation.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Command
public class SubmitResponseByStudentCommand {
    private Long examId,questionId,answerId;
    private String freeAnswer;
    private Float time;
}
