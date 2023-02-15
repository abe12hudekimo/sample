package com.example.studyJPA;

import com.example.studyJPA.dto.StudentDTO;
import com.example.studyJPA.repository.StudentRepository;
import com.example.studyJPA.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

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
    void doCreate_001() {
        StudentDTO inDto = new StudentDTO();
        inDto.setName("abe");
        inDto.setScore(10);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doCreate(inDto));
        System.out.println(error.getMessage());

    }

    @Test
    void doCreate_002() {
        StudentDTO inDto = new StudentDTO();
        inDto.setName("阿部");
        inDto.setScore(20);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doCreate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doCreate_003() {
        StudentDTO inDto = new StudentDTO();
        inDto.setName("aoyama");
        inDto.setScore(null);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doCreate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doCreate_004() {
        StudentDTO inDto = new StudentDTO();
        inDto.setName("errorHuman");
        inDto.setScore(-1);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doCreate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doCreate_005() {
        StudentDTO inDto = new StudentDTO();
        inDto.setName("errorHuman");
        inDto.setScore(101);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doCreate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doCreate_ok() {
        StudentDTO inDto = new StudentDTO();
        inDto.setName("hara");
        inDto.setScore(58);
        var studentCreate = studentService.doCreate(inDto);
        // Assertions.assertNotEquals(0, studentList.size());
    }

    @Test
    void doUpdate_001() {
        StudentDTO inDto = new StudentDTO();
        inDto.setId(5);
        inDto.setName("sakata");
        inDto.setScore(89);
        //studentService.doUpdate(inDto);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doUpdate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doUpdate_002() {
        StudentDTO inDto = new StudentDTO();
        inDto.setId(2);
        inDto.setName("abe");
        inDto.setScore(89);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doUpdate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doUpdate_003() {
        StudentDTO inDto = new StudentDTO();
        inDto.setId(1);
        inDto.setName("阿部");
        inDto.setScore(89);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doUpdate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doUpdate_004() {
        StudentDTO inDto = new StudentDTO();
        inDto.setId(1);
        inDto.setName("errorHuman");
        inDto.setScore(null);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doUpdate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doUpdate_005() {
        StudentDTO inDto = new StudentDTO();
        inDto.setId(1);
        inDto.setName("errorHuman");
        inDto.setScore(-1);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doUpdate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doUpdate_006() {
        StudentDTO inDto = new StudentDTO();
        inDto.setId(1);
        inDto.setName("errorHuman");
        inDto.setScore(101);
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doUpdate(inDto));
        System.out.println(error.getMessage());
    }

    @Test
    void doUpdate_ok() {
        StudentDTO inDto = new StudentDTO();
        inDto.setId(2);
        inDto.setName("hikata");
        inDto.setScore(83);
        var studentUpdate = studentService.doUpdate(inDto);
    }

    @Test
    void doUpdate_ok2() {
        StudentDTO inDto = new StudentDTO();
        inDto.setId(1);
        inDto.setName("abe");
        inDto.setScore(22);
        var studentUpdate2 = studentService.doUpdate(inDto);
    }

    @Test
    void doDelete_001() {
        Integer id = 3;
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doDelete(id));
        System.out.println(error.getMessage());
    }

    @Test
    void doDelete_ok() {
//             Integer id = 29;
//             var studentDelete = studentService.doDelete(id);
    }

    @Test
    void doFind_001() {
        Integer id = 3;
        var error = Assertions.assertThrows(RuntimeException.class, () -> studentService.doDelete(id));
        System.out.println(error.getMessage());
    }

    @Test
    void doFind_ok() {
        Integer id = 1;
        var studentFind = studentService.doFind(id);
    }
}