package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam,Long> {
    List<Exam> findAllByCourseIdAndAccountId(Long courseId, Long accountId);
}
