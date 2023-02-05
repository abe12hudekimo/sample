package com.example.studyJPA.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDTO implements Serializable {
	private int no;
	private String name;
	private int score;

}
