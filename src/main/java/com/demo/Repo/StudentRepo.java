package com.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

	public Student findById(int id);
}
