package io.project.students.services;

import io.project.students.model.Student;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = StudentsTestApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@WebMvcTest(value = StudentService.class, secure = false)//it works
//@TestPropertySource(locations = "classpath:application-test.properties")
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findAll() throws Exception {
        List<Student> retrieveAllStudents = studentService.retrieveAllStudents();

        System.out.println("@@@@@@@@  " + retrieveAllStudents.size());
        assertEquals(2, retrieveAllStudents.size());

    }

}
