package com.example.studyJPA.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByStudentCode(Integer studentCode);
	Optional<Student> findFirstByStudentCode(Integer studentCode);

	//List<Student> findByNameAndScore(String name);
//	@Query("SELECT * FROM student WHERE name = :name")
//    List<Student> getMembersByNameEquals(String name);
}
