package com.example.studyJPA.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		String word = "HelloWorld";
		return word.matches("[a-zA-Z0-9]{10}") + word;
	}

	@RequestMapping("/create")
	public List<Student> doCreate(StudentDTO inDto) {
		return studentService.doCreate(inDto);
	}

	@RequestMapping("/update")
	public List<Student> doUpdate(@Validated @ModelAttribute StudentDTO inDto,BindingResult result, Model m) {
		if(result.hasErrors()) {
			List<String>errorList=new ArrayList<String>();
			for(ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
				return (List<Student>) m.addAttribute("validationError", errorList);
				}	
	        }
		return studentService.doUpdate(inDto, result);
	}
	@RequestMapping("/delete")
	public List<Student> doDelete(Integer id) {
		return studentService.doDelete(id);
	}

	@RequestMapping("/result")
    public List<Student> doPost() {
		return studentService.doSearch();
    }
	
	@RequestMapping("/find")
	public Optional<Student> doIdSearch(Integer id) {
		return studentService.doFind(id);
	}
}


