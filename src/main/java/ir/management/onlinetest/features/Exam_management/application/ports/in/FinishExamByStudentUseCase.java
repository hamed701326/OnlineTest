package ir.management.onlinetest.features.Exam_management.application.ports.in;

import ir.management.onlinetest.features.Exam_management.application.ports.in.outcomes.FinishExamByStudentOutcome;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

public interface FinishExamByStudentUseCase {
    FinishExamByStudentOutcome determineScoreAutomatically(HttpServletRequest request);
}
