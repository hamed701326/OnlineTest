package ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class DeleteExamByMasterOutcome {
    boolean valid;
    Map<String,String> errorMessages =new HashMap<>();
}
