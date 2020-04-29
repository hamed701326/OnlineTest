package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.question.Answer;
import ir.management.onlinetest.entities.question.Question;
import ir.management.onlinetest.entities.take_exam.TakeAnswer;
import ir.management.onlinetest.entities.take_exam.TakeExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TakeAnswerRepository extends JpaRepository<TakeAnswer,Long> {
    TakeAnswer findByAnswerAndTakeExam(Answer answer, TakeExam takeExam);
    Optional<TakeAnswer> findByQuestionAndTakeExam(Question question, TakeExam takeExam);
    List<TakeAnswer> findAllByTakeExam(TakeExam takeExam);
    List<TakeAnswer> findAllByTakeExamId(Long takeExamId);
}
