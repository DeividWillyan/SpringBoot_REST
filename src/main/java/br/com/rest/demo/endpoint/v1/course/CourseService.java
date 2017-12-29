package br.com.rest.demo.endpoint.v1.course;

import br.com.rest.demo.exception.ResourceNotFoundException;
import br.com.rest.demo.persistence.model.Course;
import br.com.rest.demo.persistence.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class CourseService implements Serializable {

    @Autowired
    private CourseRepository courseRepository;

    public void throwResourceNotFound(Course course) {
        if (course == null || course.getId() == null || courseRepository.findOne(course.getId()) == null) {
            throw new ResourceNotFoundException("Curso não encontrado");
        }
    }

}
