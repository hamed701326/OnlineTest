package ir.management.onlinetest.features.question_management.application.ports.in.outcomes;

import ir.management.onlinetest.core.annotation.Outcome;
import ir.management.onlinetest.features.Exam_management.domain.QuestionDTOForMaster;
import ir.management.onlinetest.features.question_management.domain.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@Outcome
public class ListAllQuestionByMasterOutcome {
    List<QuestionDTO> questions;
    boolean valid;
    Map<String,String> errorMessages;
}
