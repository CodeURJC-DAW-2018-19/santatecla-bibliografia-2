package es.daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.ObraService;

@Controller
public class TemaController {

	@Autowired
	private CitaService quotes;
	
	@Autowired
	private ObraService works;
	
	@Autowired
	private AutorService authors;
	
	@Autowired
	private BookWebController webController;
	
	@RequestMapping("/tema")
	public String accederTema(Model model) {
		model.addAttribute("quotes", quotes.findAll());
		model.addAttribute("works", works.findAll());
		model.addAttribute("authors", authors.findAll());
		
		webController.addUserToModel(model);
		
		return "tema";
	}

}
