package ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes;

import ir.management.onlinetest.core.annotation.Outcome;
import ir.management.onlinetest.features.Exam_management.domain.QuestionDTOForMaster;
import lombok.*;

import java.util.List;
import java.util.Map;

@Setter @Getter
@Outcome
public class ListQuestionAndAnswerByMasterOutcome {
    List<QuestionDTOForMaster> questionAndAnswers;
    boolean valid;
    Map<String,String> errorMessages;
}
