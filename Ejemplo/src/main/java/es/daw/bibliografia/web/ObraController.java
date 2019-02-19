package es.daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraRepository;

@Controller
public class ObraController {
	
	@Autowired
	private ObraRepository repository;
	
	@RequestMapping("/obra/new") //Esto va en BookWebController Cuando se pulsa el boton de new
	public String añadirAutor(Model model, Obra obra) {
		repository.save(obra);
		return "obra"; 
	}
	
	@RequestMapping ("/obra/{id}")
	public String abrirObra(Model model, @PathVariable long id) {
		
		//Obra obra= repository.findById(id);
		
		
		return "obra"; 
	}
}
