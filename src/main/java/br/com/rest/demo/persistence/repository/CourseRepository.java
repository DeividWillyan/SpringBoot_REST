package br.com.rest.demo.persistence.repository;

import br.com.rest.demo.persistence.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @Query("select c from Course c where c.id = ?1 and c.professor = ?#{principal.professor}")
    @Override
    Course findOne(Long aLong);
}
