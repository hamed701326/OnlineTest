package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("select q from Question q join q.exams e join e.course c where c.id=?1 and q.title like %?2%")
    List<Question> findAllByCourseId( Long courseId, String title);
    List<Question> findAllByTitleContains(String title);

}
