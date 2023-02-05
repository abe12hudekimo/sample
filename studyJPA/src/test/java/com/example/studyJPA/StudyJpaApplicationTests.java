package com.example.studyJPA;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.studyJPA.service.StudentService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @BeforeEach
    void setUp() {
        System.out.println("---------------------------");
        System.out.println("test start");
        System.out.println("---------------------------");
    }

    @AfterEach
    void tearDown() {
        System.out.println("---------------------------");
        System.out.println("test end");
        System.out.println("---------------------------");
    }

    @Test
    void doCreate() {
        var studentList =  studentService.doCreate(null);
        Assertions.assertEquals(studentList.size(), 3);

    }

    @Test
    void doUpdate() {
    }

    @Test
    void doDelete() {
        var studentList =  studentService.doDelete(null);
        Assertions.assertEquals(studentList.size(), 3);
    }

    @Test
    void doSearch() {}
    
    }