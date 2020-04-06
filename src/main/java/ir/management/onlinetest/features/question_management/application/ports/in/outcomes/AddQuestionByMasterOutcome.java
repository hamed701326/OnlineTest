package ir.management.onlinetest.features.question_management.application.ports.in.outcomes;

import ir.management.onlinetest.core.annotation.Outcome;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Setter
@Getter
@Outcome
public class AddQuestionByMasterOutcome {
    boolean valid;
    Map<String,String> errorMessage;
}
