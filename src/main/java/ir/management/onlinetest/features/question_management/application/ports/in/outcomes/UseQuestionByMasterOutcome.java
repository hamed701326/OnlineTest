package ir.management.onlinetest.features.question_management.application.ports.in.outcomes;

import ir.management.onlinetest.core.annotation.Outcome;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
@Outcome
public class UseQuestionByMasterOutcome {
    boolean valid;
    Map<String,String> errorMessages=new HashMap<>();

}
