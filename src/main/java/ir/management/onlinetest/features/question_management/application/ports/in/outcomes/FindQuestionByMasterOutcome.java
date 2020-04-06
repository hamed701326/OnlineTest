package ir.management.onlinetest.features.question_management.application.ports.in.outcomes;

import ir.management.onlinetest.core.annotation.Outcome;
import ir.management.onlinetest.entities.question.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@Outcome
public class FindQuestionByMasterOutcome {
    boolean valid;
    Map<String,String> errorMessage;
    List<Question> questions;
}
