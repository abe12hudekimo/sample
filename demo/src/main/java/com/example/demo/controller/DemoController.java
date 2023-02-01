package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.StudentDAO;
import com.example.demo.dto.StudentDTO;

@Controller
public class DemoController {
	
	@RequestMapping("/")
	public String start() {
		return "search.html";
	}
	
	@RequestMapping("/result")
    public String doPost(Model m) {

        StudentDAO sdao = new StudentDAO();

        StudentDTO sdto = sdao.select();
        m.addAttribute("sdto", sdto);
        return "showstudent.html";
        
    }
}
/*
@RequestMapping("/register")
public String result
(@Validated @ModelAttribute StudentInfo si, BindingResult result,
 Model m) {


	m.addAttribute("si", si);
	return "showstudent.html";
}
*/

