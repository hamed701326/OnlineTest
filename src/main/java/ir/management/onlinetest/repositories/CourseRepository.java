package ir.management.onlinetest.repositories;

import ir.management.onlinetest.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
