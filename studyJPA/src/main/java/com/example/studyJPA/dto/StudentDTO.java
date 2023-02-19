package com.example.studyJPA.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO implements Serializable {
	private Integer id;
	@NotNull
	private String name;
	@NotNull
	private Integer score;

	@NotNull
	private Integer stuId;

}
