package br.com.rest.demo.endpoint.v1.course;

import br.com.rest.demo.persistence.model.Course;
import br.com.rest.demo.persistence.repository.CourseRepository;
import br.com.rest.demo.util.EndpointUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/professor/course")
@Api(description = "Operações relacionadas aos cursos do professor")
public class CourseEndpoint {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EndpointUtil endpointUtil;

    @ApiOperation(value = "Retorna o curso baseado no id", response = Course.class)
    @GetMapping(path = "{id}")
    public ResponseEntity<?> getCourseById(@PathVariable long id) {
        Course course = courseRepository.findOne(id);
        return endpointUtil.returnObjectOrNotFound(course);
    }


}
