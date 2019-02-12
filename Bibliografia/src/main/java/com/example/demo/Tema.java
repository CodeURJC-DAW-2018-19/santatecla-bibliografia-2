package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Tema{

	@RequestMapping("/tema")
	public String greeting(Model model) {

		return "tema";
	}

}