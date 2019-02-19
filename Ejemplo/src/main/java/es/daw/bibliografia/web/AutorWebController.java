package es.daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.BookService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.TemaService;

@Controller
public class AutorWebController {
	
	@Autowired
	private ObraService obraService;
	
	@Autowired
	private TemaService temaService;
	
	@Autowired
	private CitaService citaService;
	
	@RequestMapping("/autor")
	public String showAutor(Model model) {
		model.addAttribute("obras", obraService.findAll());
		model.addAttribute("temas", temaService.findAll());
		model.addAttribute("citas", citaService.findAll());
		return "autor";
	}
	
}
