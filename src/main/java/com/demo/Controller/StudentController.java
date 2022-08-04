package com.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Exception.RecordNotFoundException;
import com.demo.Model.Response;
import com.demo.Model.Student;
import com.demo.Service.StudentService;

//@CrossOrigin(origins="http://localhost:4200/")

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

	@Autowired
	StudentService service;

	@GetMapping("/getStudent")
	public ResponseEntity<List<Student>> getStudent() {

		List<Student> student = service.getStudent();
		return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
	}

	@GetMapping("/getStudentById/{id}")
	public ResponseEntity<Student> fetchStudentById(@PathVariable("id") int id) {

		Student student = null;
		return new ResponseEntity<Student>(service.getStudentById(id), HttpStatus.OK);

	}

	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@Validated @RequestBody Student student) {

		return new ResponseEntity<Student>(service.addStudent(student), HttpStatus.CREATED);
	}

}
