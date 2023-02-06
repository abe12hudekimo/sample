package com.example.studyJPA.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "student")
@NoArgsConstructor
public class Student {
	
    @Id
    @Column(name = "no", nullable = false)
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "score")
    private Integer score;


}
