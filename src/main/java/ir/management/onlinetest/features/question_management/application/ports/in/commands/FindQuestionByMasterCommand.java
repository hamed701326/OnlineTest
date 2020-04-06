package ir.management.onlinetest.features.question_management.application.ports.in.commands;

import ir.management.onlinetest.core.annotation.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
@Command
public class FindQuestionByMasterCommand {
    private String title;
    private Long courseId;
}
