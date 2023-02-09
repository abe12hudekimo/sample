package com.example.studyJPA.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO implements Serializable {
	private Integer id;
	private String name;
	private Integer score;

}
