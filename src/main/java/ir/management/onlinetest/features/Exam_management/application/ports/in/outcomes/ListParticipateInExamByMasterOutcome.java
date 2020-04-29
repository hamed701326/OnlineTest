package ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes;

import ir.management.onlinetest.core.annotation.Outcome;
import ir.management.onlinetest.features.Exam_management.domain.Participator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Outcome
@Getter@Setter
public class ListParticipateInExamByMasterOutcome {
    List<Participator> participateList;
    boolean valid;
    Map<String,String> errorMessages;
}
