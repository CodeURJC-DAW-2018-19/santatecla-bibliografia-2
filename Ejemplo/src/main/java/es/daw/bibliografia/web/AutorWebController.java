package es.daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.BookService;
import es.daw.bibliografia.book.ObraService;

@Controller
public class AutorWebController {
	
	@Autowired
	private ObraService obraService;
	
	@RequestMapping("/autor")
	public String showAutor(Model model) {
		model.addAttribute("obras", obraService.findAll());
		return "autor";
	}
	
}
