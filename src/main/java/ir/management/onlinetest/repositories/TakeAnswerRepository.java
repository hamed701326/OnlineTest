package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.question.Answer;
import ir.management.onlinetest.entities.take_exam.TakeAnswer;
import ir.management.onlinetest.entities.take_exam.TakeExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakeAnswerRepository extends JpaRepository<TakeAnswer,Long> {
    TakeAnswer findByAnswerAndTakeExam(Answer answer, TakeExam takeExam);
    List<TakeAnswer> findAllByTakeExam(TakeExam takeExam);
}
