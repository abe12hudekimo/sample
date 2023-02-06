package com.example.studyJPA.repository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Integer> {
//	@Query("SELECT * FROM student WHERE name = :name")
//    List<Student> getMembersByNameEquals(String name);
}
