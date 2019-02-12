package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Inicio{

	@RequestMapping("/inicio")
	public String greeting(Model model) {

		model.addAttribute("name", "World");

		return "inicio";
	}

}