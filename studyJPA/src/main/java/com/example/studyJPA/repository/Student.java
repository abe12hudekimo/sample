package com.example.studyJPA.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student {
	
    @Id
    @Column(name = "no", nullable = false)
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "score")
    private Integer score;


}
