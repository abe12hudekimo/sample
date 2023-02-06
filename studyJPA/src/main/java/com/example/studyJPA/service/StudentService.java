package com.example.studyJPA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studyJPA.dto.StudentDTO;
import com.example.studyJPA.repository.Student;
import com.example.studyJPA.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public List<Student> doSearch() {
		return studentRepository.findAll();
	}

	public List<Student> doCreate(StudentDTO inDto) {
		Student student = new Student();
		student.setId(10);
		student.setName("osaki");
		student.setScore(44);
		studentRepository.saveAndFlush(student);
		return doSearch();
	}

	
	public List<Student> doUpdate(StudentDTO inDto) {
		Optional<Student> studentOptional = studentRepository.findById(10);
		studentOptional.ifPresent(student -> {
			student.setScore(90);
			studentRepository.saveAndFlush(student);
		});
		return doSearch();
	}

	public List<Student> doDelete(Integer id) {
		studentRepository.deleteById(10);
		return doSearch();
	}
	
	public Optional<Student> doFind(Integer id) {
		Optional<Student> studentOptional = studentRepository.findById(2);
		return studentOptional;
		
	}
	

}
