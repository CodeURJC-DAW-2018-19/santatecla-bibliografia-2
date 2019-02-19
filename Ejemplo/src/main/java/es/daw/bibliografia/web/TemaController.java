package es.daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.CitaService;


@Controller
public class TemaController {
	
	@Autowired
	private CitaService service;
	
	
	@RequestMapping("/tema")
	public String accederTema(Model model) {
	
		model.addAttribute("quotes", service.findAll());
		return "tema";
	}
	
}
