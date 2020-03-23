package ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class EditExamByMasterOutcome {
    boolean valid;
    Map<String,String> errorMessage;
}
