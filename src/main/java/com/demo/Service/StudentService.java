package com.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Model.Student;
import com.demo.Repo.StudentRepo;

@Service
public class StudentService  {

	@Autowired
	StudentRepo repo;

	public List<Student> getStudent() {
		return repo.findAll();
	}

	public Student addStudent(Student student) {
		return repo.save(student);
	}

	public Student getStudentById(int id)

	{
		return repo.findById(id);

	}
}
