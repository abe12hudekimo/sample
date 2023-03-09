package com.example.studyJPA.controller;

import com.example.studyJPA.dto.StudentDTO;
import com.example.studyJPA.repository.Student;
import com.example.studyJPA.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/find")
    public Optional<Student> doFind(@RequestBody Integer id) {
        return studentService.doFind(id);
    }

    @RequestMapping("/result")
    public List<Student> showStudent() {
        return studentService.showStudent();
    }

    @RequestMapping("/")
    public String start() {
        String word = "HelloWorld";
        return word.matches("[a-zA-Z0-9]{10}") + word;
    }

    @RequestMapping("/create")
    public List<Student> doCreate(@RequestBody StudentDTO inDto) {
        return studentService.doCreate(inDto);
    }

    @RequestMapping("/update")
    public List<Student> doUpdate(@RequestBody StudentDTO inDto) {
        return studentService.doUpdate(inDto);
    }

    @RequestMapping("/delete")
    public List<Student> doDelete(@RequestBody Integer id) {
        return studentService.doDelete(id);
    }

}

