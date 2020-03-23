package ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes;

import ir.management.onlinetest.features.Exam_management.domain.ExamDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class ListExamByMasterOutcome {
    List<ExamDTO> examList;
    boolean validated;
    Map<String,String> errorMessage;

}
