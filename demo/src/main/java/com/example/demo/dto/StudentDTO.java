package com.example.demo.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.demo.repository.StudentInfo;

public class StudentDTO implements Serializable{
    private ArrayList<StudentInfo> list;

    public StudentDTO(){
        list = new ArrayList<StudentInfo>();
    }
    public void add(StudentInfo si){
        list.add(si);
    }
    public StudentInfo get(int i){
        return list.get(i);
    }
    public int size(){
        return list.size();
    }
}


