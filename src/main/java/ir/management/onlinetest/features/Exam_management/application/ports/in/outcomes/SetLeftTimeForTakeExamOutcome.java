package ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Setter
@Getter
public class SetLeftTimeForTakeExamOutcome {
    boolean valid;
    Map<String,String> errorMessages;
}
