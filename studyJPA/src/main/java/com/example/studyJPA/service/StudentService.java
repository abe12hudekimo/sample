package com.example.studyJPA.service;

import com.example.studyJPA.dto.StudentDTO;
import com.example.studyJPA.repository.Student;
import com.example.studyJPA.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

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
    public List<Student> showStudent() {
        return studentRepository.findAll();
    }

    /**
     * doFindメソッド
     * 入力された生徒番号を元にその生徒の情報を表示
     *
     * @param id 生徒番号の入力情報
     * @return studentOptional　検索した生徒を表示
     */
    public Optional<Student> doFind(Integer id) {
        // 学生番号から対象のオブジェクトを取得する
        Optional<Student>studentList = studentRepository.findById(id);
        if (studentList.isEmpty()) {
            throw new RuntimeException("学生番号が存在しません。");
        }
        return studentList;
    }

    /**
     * doCheckメソッド
     * 名前の重複または未入力のチェック
     *
     * @param inDto 生徒の入力情報
     */
    private void doCheck(StudentDTO inDto) {
        String classNo = inDto.getClassNo();
        String name = inDto.getName();
        int score = inDto.getScore();

        // 生徒番号が未記入かチェック
        if (!classNo.matches("[1-3][ABC]")) {
            throw new RuntimeException("正しい所属を入力して下さい");
        }
        // 名前未記入のチェック
        if (name.isEmpty()) {
            throw new RuntimeException("名前を入力して下さい");
        }
        // ユーザーのローマ字のチェック
        if (!name.matches(regex_AlphaNum)) {
            throw new RuntimeException("名前は半角英数字で入力して下さい");
        }
        // スコアが未記入かチェック
        if ((Integer)score == null) {
            throw new RuntimeException("スコアを入力して下さい");
        }
        // スコアが０から１００までかチェック
        if (!(score <= MAX & score >= MIN)) {
            throw new RuntimeException("スコアは０から１００までで入力して下さい");
        }

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
        student.setClassNo(inDto.getClassNo());
        student.setName(inDto.getName());
        student.setScore(inDto.getScore());
        //生徒の情報をDBに保存
        studentRepository.saveAndFlush(student);
        return showStudent();
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
        Student student = studentRepository.findById(inDto.getId()).orElseThrow(() ->
                new RuntimeException("学生番号が存在しません。")
        );
        student.setClassNo(inDto.getClassNo());
        student.setName(inDto.getName());
        student.setScore(inDto.getScore());
        //生徒の情報をDBに保存
        studentRepository.saveAndFlush(student);
        return showStudent();
    }

    /**
     * doDeleteメソッド
     * 入力された生徒番号を元に、生徒を削除する
     *
     * @param id 生徒番号
     * @return doSearchメソッド 処理後の生徒全員の情報を表示
     */
    public List<Student> doDelete(Integer id) {
        // DBに検索した生徒がいるかどうかID検索のチェック
        doFind(id);
        //生徒の情報を削除
         studentRepository.deleteById(id);
        return showStudent();
    }
}
