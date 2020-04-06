package ir.management.onlinetest.features.question_management.application.ports.in.commands;

import com.sun.istack.NotNull;
import ir.management.onlinetest.core.annotation.Command;
import ir.management.onlinetest.entities.question.Answer;
import ir.management.onlinetest.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Command
public class AddQuestionByMasterCommand {
    private String type;
    private String title,content;
    private Level level;
    private float score;
    List<Answer> answers;
    private Long examId;
    private String answer;
}
