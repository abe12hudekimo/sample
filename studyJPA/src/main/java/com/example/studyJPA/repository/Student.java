package com.example.studyJPA.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private Integer id;
      
    @Column(name = "name")
//    @NotEmpty(message = "名前を入力してください")
//    @Size(max=10, message = "名前は10文字以内で入力してください")
     private String name;
    
    @Column(name = "score")
    @Builder.Default
    private Integer score = 50;


}
