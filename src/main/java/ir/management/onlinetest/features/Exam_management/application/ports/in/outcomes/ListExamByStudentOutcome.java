package ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes;

import ir.management.onlinetest.core.annotation.Outcome;
import ir.management.onlinetest.features.Exam_management.domain.ExamDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@Outcome
public class ListExamByStudentOutcome {
    List<ExamDTO> examList;
    boolean valid;
    Map<String,String> errorMessage;
}
