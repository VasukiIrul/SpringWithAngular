package com.demo.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.demo.Model.Student;


@Service
public interface IStudentService {

	public List<Student> getStudent();
	public Student addStudent(Student student);
	public Student  getStudentById(int id);
}
