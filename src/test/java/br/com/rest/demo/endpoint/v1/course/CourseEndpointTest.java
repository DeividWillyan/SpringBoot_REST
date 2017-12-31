package br.com.rest.demo.endpoint.v1.course;

import br.com.rest.demo.endpoint.v1.ProfessorEndpointTest;
import br.com.rest.demo.persistence.model.Course;
import br.com.rest.demo.persistence.repository.CourseRepository;
import br.com.rest.demo.persistence.repository.ProfessorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CourseEndpointTest {

    @MockBean
    ProfessorRepository professorRepository;
    @MockBean
    private CourseRepository courseRepository;
    @Autowired
    private TestRestTemplate testRestTemplate;
    private HttpEntity<Void> professorHeader;
    private HttpEntity<Void> wrongHeader;
    private Course course = mockCourse();

    private static Course mockCourse() {
        return Course.Builder.newCourse()
                .id(1L)
                .nome("Curso de Java")
                .professor(ProfessorEndpointTest.mockProfessor())
                .build();
    }

    @Before
    public void configProfessorHeader() {
        String body = "{\"username\":\"willyan\",\"password\":\"deivid\"}";
        HttpHeaders headers = testRestTemplate.postForEntity("/login", body, String.class).getHeaders();
        this.professorHeader = new HttpEntity<>(headers);
    }

    @Before
    public void configWrongHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "vaidapau");
        this.professorHeader = new HttpEntity<>(headers);
    }

    @Before
    public void setup() {
        BDDMockito.when(courseRepository.findOne(course.getId())).thenReturn(course);
        BDDMockito.when(courseRepository.findAllCourses("")).thenReturn(Collections.singletonList(course));
        BDDMockito.when(courseRepository.findAllCourses("Curso")).thenReturn(Collections.singletonList(course));
    }

    @Test
    public void getCourseById() throws Exception {
        ResponseEntity<String> exchange = testRestTemplate
                .exchange("/v1/professor/course/1", HttpMethod.GET, wrongHeader, String.class);
        assertThat(exchange.getStatusCodeValue()).isEqualTo(403);
    }

    @Test
    public void getAllCourses() throws Exception {
        ResponseEntity<String> exchange = testRestTemplate
                .exchange("/v1/professor/course/list", HttpMethod.GET, wrongHeader, String.class);
        assertThat(exchange.getStatusCodeValue()).isEqualTo(403);
    }

    @Test
    public void getAllCoursesWhenNameDoesNotExist() throws Exception {
        ResponseEntity<String> exchange = testRestTemplate
                .exchange("/v1/professor/course/list?name=naoexiste", HttpMethod.GET, professorHeader, String.class);
        assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void getAllCoursesWhenNameExistReturn200() throws Exception {
        ResponseEntity<String> exchange = testRestTemplate
                .exchange("/v1/professor/course/list?name=Curso", HttpMethod.GET, professorHeader, String.class);
        assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
    }


}