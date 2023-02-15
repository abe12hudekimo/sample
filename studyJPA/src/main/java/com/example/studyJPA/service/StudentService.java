package com.example.studyJPA.service;

import com.example.studyJPA.dto.StudentDTO;
import com.example.studyJPA.repository.Student;
import com.example.studyJPA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    String regex_AlphaNum = "^[A-Za-z0-9]+$";
    Integer MIN = 0;
    Integer MAX = 100;


    public List<Student> doSearch() {
        return studentRepository.findAll();
    }

    public Optional<Student> doFind(@Validated Integer id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        // ID検索のチェック
        if (studentOptional.isEmpty()) {
            throw new RuntimeException("ユーザーが存在しません");
        }
        return studentOptional;
    }


    public void doCheck(StudentDTO inDto) {
        String name = inDto.getName();
        Integer score = inDto.getScore();
        // ユーザーのローマ字のチェック
        if (!name.matches(regex_AlphaNum)) {
            throw new RuntimeException("名前は半角英数字で入力して下さい");
        }
        // 名前未記入のチェック
        if (name.isEmpty()) {
            throw new RuntimeException("名前を入力して下さい");
        }
        // スコアのチェック
        if (score == null) {
            throw new RuntimeException("スコアを入力して下さい");
        }
        // スコアが０から１００までかチェック
        if (!(score <= MAX & score >= MIN)) {
            throw new RuntimeException("スコアは０から１００までで入力して下さい");
        }
    }

    public List<Student> doCreate(@RequestBody StudentDTO inDto) {
        var studentOpt = studentRepository.findByName(inDto.getName());
        //ユーザーの存在チェック
        if (studentOpt.isPresent()) {
            throw new RuntimeException("ユーザーが既に存在しています。");
        }
        doCheck(inDto);

        Student student = new Student();

        student.setName(inDto.getName());
        student.setScore(inDto.getScore());
        studentRepository.saveAndFlush(student);
        return doSearch();
    }


    public List<Student> doUpdate(@RequestBody StudentDTO inDto) {
        var studentOpt = studentRepository.findByName(inDto.getName());
        Optional<Student> studentOptional = studentRepository.findById(inDto.getId());
        // ID検索のチェック
        doFind(inDto.getId());
        //ユーザーの存在チェック
        if (studentOpt.isPresent()) {
            throw new RuntimeException("同じ名前のユーザーが既に存在しています。");
        }
        doCheck(inDto);

        studentOptional.ifPresent(student -> {
            student.setName(inDto.getName());
            student.setScore(inDto.getScore());
            studentRepository.saveAndFlush(student);
        });
        return doSearch();
    }

    public List<Student> doDelete(@RequestBody Integer id) {
        doFind(id);
        studentRepository.deleteById(id);
        return doSearch();
    }


}
