package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.Account;
import ir.management.onlinetest.entities.Course;
import ir.management.onlinetest.entities.Exam;
import ir.management.onlinetest.enums.Status;
import ir.management.onlinetest.enums.StatusExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam,Long> {
    List<Exam> findAllByCourseIdAndAccountId(Long courseId, Long accountId);
    List<Exam> findAllByCourseId(Long courseId);
    Optional<Exam> findByCourseIdAndAccountIdAndStatus(Long courseId, Long accountId, StatusExam status);
}
