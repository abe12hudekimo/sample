package com.example.demo.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.demo.dto.StudentDTO;
import com.example.demo.repository.StudentInfo;

public class StudentDAO {
    private final String URL = "jdbc:mysql://localhost/sampledb";
    private final String USER = "root";
    private final String PASS = "pass";
    private Connection con = null;

    public String sqlSentence(int no) {
    	String sql = "SELECT * FROM student WHERE id =" + no ;
    	return sql;
    }
    public void connect(){
        try{

            con = DriverManager.getConnection(URL, USER, PASS);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public StudentDTO select() {
        Statement stmt = null;
        ResultSet rs = null;
        StudentDTO sdto = new StudentDTO();
     
        try{
            connect();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlSentence(1));
            //while(rs.next()){
                StudentInfo si = new StudentInfo();
                si.setNo(rs.getInt("no"));
                si.setName(rs.getString("name"));
                si.setScore(rs.getInt("score"));
                sdto.add(si);
            //}
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        disconnect();
        return sdto;
    }
    public void disconnect(){
        try{
            if(con != null) con.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
