package com.example.demo.repository;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

import java.io.Serializable;


@Setter
@Getter
public class StudentInfo implements Serializable {
    private int no;
    private String name;
    private int score;

}
