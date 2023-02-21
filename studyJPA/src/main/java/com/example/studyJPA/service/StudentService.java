package com.example.studyJPA.service;

import com.example.studyJPA.dto.StudentDTO;
import com.example.studyJPA.repository.Student;
import com.example.studyJPA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviceクラス
 * CRUDの中身を行うメソッドのプログラム
 *
 * @author abe
 */
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    final String regex_AlphaNum = "^[A-Za-z0-9]+$";
    final int MIN = 0;
    final int MAX = 100;

    /**
     * doSearchメソッド
     * 生徒全員の情報を表示
     *
     * @return studentRepository.findAll() テーブル内にある生徒全員を表示
     */
    public List<Student> doSearch() {
        return studentRepository.findAll();
    }

    /**
     * doFindメソッド
     * 入力された生徒番号を元にその生徒の情報を表示
     *
     * @param studentCode 生徒番号の入力情報
     * @return studentOptional　検索した生徒を表示
     */
    public Student doFind(Integer studentCode) {
        List<Student> studentInfo = studentRepository.findByStudentCode(studentCode);
        Student student = (Student) studentInfo;

        // ID検索のチェック
//        if (student.isEmpty()) {
//            throw new RuntimeException("ユーザーが存在しません");
//        }
        return student;
    }

    /**
     * doCheckメソッド
     * 名前の重複または未入力のチェック
     *
     * @param inDto 生徒の入力情報
     */
    private void doCheck(StudentDTO inDto) {
        String name = inDto.getName();
        int score = inDto.getScore();
        int studentCode = inDto.getStudentCode();
        List<Student> studentOptional = studentRepository.findByStudentCode(studentCode);

        // 名前未記入のチェック
        if (name.isEmpty()) {
            throw new RuntimeException("名前を入力して下さい");
        }
        // スコアが未記入かチェック
        if ((Integer) score == null) {
            throw new RuntimeException("スコアを入力して下さい");
        }
        // 生徒番号が未記入かチェック
        if ((Integer) studentCode == null) {
            throw new RuntimeException("名前を入力して下さい");
        }
        // ユーザーのローマ字のチェック
        if (!name.matches(regex_AlphaNum)) {
            throw new RuntimeException("名前は半角英数字で入力して下さい");
        }
        // スコアが０から１００までかチェック
        if (!(score <= MAX & score >= MIN)) {
            throw new RuntimeException("スコアは０から１００までで入力して下さい");
        }
        //ユーザーの存在チェック
//        if (studentOptional.isPresent()) {
//            throw new RuntimeException("既に同じ生徒番号が存在しています。");
//        }
    }

    /**
     * doCreateメソッド
     * 入力された生徒番号を元に生徒を新規で登録する
     *
     * @param inDto 生徒の入力情報
     * @return doSearchメソッド 処理後の生徒全員の情報を表示
     */
    public List<Student> doCreate(StudentDTO inDto) {
        //入力された値にnullがないかのエラーチェック
        doCheck(inDto);
        //生徒を登録
        Student student = new Student();
        student.setStudentCode(inDto.getStudentCode());
        student.setName(inDto.getName());
        student.setScore(inDto.getScore());
        //生徒の情報をDBに保存
        studentRepository.saveAndFlush(student);
        return doSearch();
    }

    /**
     * doUpdateメソッド
     * 入力された生徒番号を元に既存の生徒の情報を更新する
     *
     * @param inDto 生徒の入力情報
     * @return doSearchメソッド 処理後の生徒全員の情報を表示
     */
    public List<Student> doUpdate(StudentDTO inDto) {
        //入力された値にnullがないかのエラーチェック
        doCheck(inDto);
        // DBに検索した生徒がいるかどうかID検索のチェック
        doFind(inDto.getId());
        //生徒の情報を更新
        Student student = doFind(inDto.getStudentCode());
        student.setName(inDto.getName());
        student.setScore(inDto.getScore());
        //生徒の情報をDBに保存
        studentRepository.saveAndFlush(student);

        return doSearch();
    }

    /**
     * doDeleteメソッド
     * 入力された生徒番号を元に、生徒を削除する
     *
     * @param studentCode 生徒番号
     * @return doSearchメソッド 処理後の生徒全員の情報を表示
     */
    public List<Student> doDelete(Integer studentCode) {
        // DBに検索した生徒がいるかどうかID検索のチェック
        doFind(studentCode);
        //生徒の情報を削除
       // studentRepository.deleteByStudentCode(studentCode);
        return doSearch();
    }
}
