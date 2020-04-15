package ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes;

import ir.management.onlinetest.entities.question.Question;
import ir.management.onlinetest.features.Exam_management.adapter.web.ExamController;
import ir.management.onlinetest.features.Exam_management.domain.ExamDTO;
import ir.management.onlinetest.features.question_management.domain.QuestionDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@Setter
public class TakeExamByStudentOutcome {
    List<QuestionDTO> questions;
    boolean valid;
    Map<String,String> errorMessages=new HashMap<>();
    Double time;
}
