package br.com.rest.demo.endpoint.v1.course;

import br.com.rest.demo.persistence.model.Course;
import br.com.rest.demo.persistence.repository.CourseRepository;
import br.com.rest.demo.util.EndpointUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/professor/course")
@Api(description = "Operações relacionadas aos cursos do professor")
public class CourseEndpoint {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EndpointUtil endpointUtil;

    @ApiOperation(value = "Retorna o curso baseado no id", response = Course.class)
    @GetMapping(path = "{id}")
    public ResponseEntity<?> getCourseById(@PathVariable long id) {
        return endpointUtil.returnObjectOrNotFound(courseRepository.findOne(id));
    }

    @ApiOperation(value = "Retorna uma lista de cursos do professor", response = Course.class)
    @GetMapping(path = "list")
    public ResponseEntity<?> getAllCourses(@ApiParam("Nome do curso") @RequestParam(value = "name", defaultValue = "") String name) {
        return endpointUtil.returnObjectOrNotFound(courseRepository.findAllCourses(name));
    }

    @ApiOperation(value = "Exclui o curso baseado no id e retorna Status 200/.")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        courseService.throwResourceNotFound(courseRepository.findOne(id));
        courseRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza o curso baseado no id e retorna Status 200/.")
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Course course) {
        courseService.throwResourceNotFound(courseRepository.findOne(course));
        courseRepository.save(course);
        return new ResponseEntity<>(HttpStatus.OK);
    }















}
