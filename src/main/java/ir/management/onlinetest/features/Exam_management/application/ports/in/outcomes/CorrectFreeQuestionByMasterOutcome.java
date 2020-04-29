package ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes;

import ir.management.onlinetest.core.annotation.Outcome;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Outcome
public class CorrectFreeQuestionByMasterOutcome {
    boolean valid;
    Map<String,String> errorMessages;
}
