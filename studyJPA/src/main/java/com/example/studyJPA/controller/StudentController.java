package com.example.studyJPA.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studyJPA.dto.StudentDTO;
import com.example.studyJPA.repository.Student;
import com.example.studyJPA.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/")
	public String start() {
		return "hello wold!";
	}

	@RequestMapping("/create")
	public List<Student> doCreate(StudentDTO inDto) {
		return studentService.doCreate(inDto);
	}

	@RequestMapping("/update")
	public List<Student> doUpdate(StudentDTO inDto) {
		return studentService.doUpdate(inDto);
	}
	@RequestMapping("/delete")
	public List<Student> doDelete(Integer id) {
		return studentService.doDelete(id);
	}

	@RequestMapping("/result")
    public List<Student> doPost() {
		return studentService.doSearch();
    }

}


