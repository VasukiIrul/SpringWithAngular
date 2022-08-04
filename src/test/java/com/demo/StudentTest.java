package com.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.demo.Controller.StudentController;
import com.demo.Model.Student;
import com.demo.Service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

//Spring Runner  : which provides functionality of the Spring TestContext Framework to 
//standard JUnit tests by means of the TestContextManager and associated support classes and annotations.

//@WebMvcTest annotation is used for Spring MVC tests.
// It disables full auto-configuration and instead apply only configuration relevant to MVC tests.

//@RunWith(SpringRunner.class)

@WebMvcTest(controllers = StudentController.class)
public class StudentTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	StudentController studentController;

	@MockBean
	StudentService studentService;

	/*
	 * @Before public void setUp() { mockMvc =
	 * MockMvcBuilders.standaloneSetup(studentController).build(); }
	 */

	@Test
	public void getAllStudentById() throws Exception {
		// mock the data return by the student service

		Student student = new Student();
		student.setId(123);
		student.setName("vasu");
		student.setCity("chennai");

		when(studentService.getStudentById(anyInt())).thenReturn(student);

		// create a mock http request to verify the expected result
		mockMvc.perform(MockMvcRequestBuilders.get("/student/getStudentById/124")).andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(123))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("vasu"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.city").value("chennai")).andExpect(status().isOk());

	}

	@Test
	public void saveUserTest() throws Exception {
		// mock the user data that we have to insert

		Student student = new Student();
		student.setId(111);
		student.setName("Rose");
		student.setCity("chennai");

		when(studentService.addStudent(any(Student.class))).thenReturn(student);

		// mock request user

		mockMvc.perform(MockMvcRequestBuilders.post("/student/addStudent")
				.content(new ObjectMapper().writeValueAsString(student))
				.contentType(MediaType.APPLICATION_ATOM_XML.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(111))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Rose"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.city").value("chennai"));

	}
	
	 @Test
	    public void getAllStudentdata() throws Exception {
		 
	        List<Student> students = Arrays.asList(new Student(1,"vasu","Chennai"));
	        
	        Mockito.when(studentService.getStudent()).thenReturn(students);
	        
	        mockMvc.perform(MockMvcRequestBuilders.get("/student/getStudent"))
	                .andExpect(MockMvcResultMatchers.status().isOk());

	    }

}
