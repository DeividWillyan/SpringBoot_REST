package br.com.rest.demo.persistence.repository;

import br.com.rest.demo.persistence.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @Query("select c from Course c where c.id = ?1 and c.professor = ?#{principal.professor}")
    Course findOne(Long id);

    @Query("select c from Course c where c = ?1 and c.professor = ?#{principal.professor}")
    Course findOne(Course course);

    @Query("select c from Course c where c.nome like %?1% and c.professor = ?#{principal.professor}")
    List<Course> findAllCourses(String name);
}
