package com.example.studyJPA.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

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
		 //ユーザーの存在チェック
//		var studentOpt = studentRepository.findByName(inDto.getName());
//		if (studentOpt.isPresent()) {
//            throw new RuntimeException("ユーザーが既に存在しています。");
//        }
		// ユーザーのローマ字のチェック
		var studentNameOpt = inDto.getName().matches("[a-zA-Z]");
		if (studentNameOpt) {}else {
			 throw new RuntimeException("名前はローマ字で入力して下さい");
		}
        // スコアのチェック
		
		Student student = new Student();
//		student.setId(inDto.getNo());
		student.setName(inDto.getName());
		student.setScore(inDto.getScore());
		studentRepository.saveAndFlush(student);
		return doSearch();
	}

	
	public List<Student> doUpdate(@Validated StudentDTO inDto, BindingResult result) {
		Optional<Student> studentOptional = studentRepository.findById(inDto.getId());
		studentOptional.ifPresent(student -> {
			student.setName(inDto.getName());
			student.setScore(inDto.getScore());
			studentRepository.saveAndFlush(student);
		});
		return doSearch();
	}

	public List<Student> doDelete(@Validated Integer id) {
		studentRepository.deleteById(id);
		return doSearch();
	}
	
	public Optional<Student> doFind(@Validated Integer id) {
		Optional<Student> studentOptional = studentRepository.findById(id);
		return studentOptional;
		
	}
	

}
