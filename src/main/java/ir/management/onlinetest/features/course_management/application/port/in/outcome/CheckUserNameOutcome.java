package ir.management.onlinetest.features.course_management.application.port.in.outcome;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckUserNameOutcome {
    String message;
    boolean validated;
}
