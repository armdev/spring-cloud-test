package io.project.students.resources;

import io.project.students.StudentsApplication;
import io.project.students.model.Course;
import io.project.students.services.StudentService;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
//@SpringBootTest(classes = StudentsApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    
    private Course mockCourse;
    private String exampleCourseJson ="{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]};";

    @Before
    public void setUp() throws Exception {
         mockCourse = new Course("Course1", "Spring", "10 Steps",
                Arrays.asList("Learn Maven", "Import Project", "First Example",
                        "Second Example"));

    }

    @Test
    public void retrieveDetailsForCourse() throws Exception {

        Mockito.when(
                studentService.retrieveCourse(Mockito.anyString(),
                        Mockito.anyString())).thenReturn(mockCourse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/ysu/students/Student1/courses/Course1").accept(
                        MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //System.out.println("#########1 " + result.getResponse());
       String expected = "{\"id\":\"Course1\",\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

        System.out.println("#########2expected  " + expected);
        
          System.out.println("#########3result  " + result.getResponse().getContentAsString());

        
      assertEquals(expected,  result.getResponse().getContentAsString());

       // JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createStudentCourse() throws Exception {
        Course mockCourse = new Course("1", "Smallest Number", "1",
                Arrays.asList("1", "2", "3", "4"));

        // studentService.addCourse to respond back with mockCourse
        Mockito.when(
                studentService.addCourse(Mockito.anyString(),
                        Mockito.any(Course.class))).thenReturn(mockCourse);

        // Send course as body to /students/Student1/courses
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/ysu/students/Student1/courses")
                .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/api/v1/ysu/students/Student1/courses/1",
                response.getHeader(HttpHeaders.LOCATION));

    }

}
