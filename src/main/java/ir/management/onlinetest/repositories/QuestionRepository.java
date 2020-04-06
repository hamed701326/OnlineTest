package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("select q from  Exam e join e.questions q join e.course c where  c.id=?1")
    Map<Question,Float> findAllByCourseId( Long courseId);
}
