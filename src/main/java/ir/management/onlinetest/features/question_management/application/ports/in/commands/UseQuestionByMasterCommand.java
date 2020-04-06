package ir.management.onlinetest.features.question_management.application.ports.in.commands;

import ir.management.onlinetest.core.annotation.Command;
import ir.management.onlinetest.entities.question.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Command
public class UseQuestionByMasterCommand {
    private Long examId;
    private Long questionId;
    private boolean edited;
}
