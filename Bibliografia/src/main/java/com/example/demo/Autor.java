package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Autor{
	

	@RequestMapping("/autor")
	public String greeting(Model model) {

		return "autor";
	}

}