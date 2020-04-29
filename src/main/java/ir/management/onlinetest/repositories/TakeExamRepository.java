package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.entities.Exam;
import ir.management.onlinetest.entities.take_exam.TakeExam;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TakeExamRepository extends JpaRepository<TakeExam,Long> {
    Optional<TakeExam> findByExamAndAccount(Exam exam, Account account);
    List<TakeExam> findAllByExam(Exam exam);
}
