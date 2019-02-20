package es.daw.bibliografia.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.TemaService;

@Controller
public class ObraController {

	@Autowired
	private ObraService service;
	
	@Autowired
	private TemaService serviceTema;

	@Autowired
	private AutorService serviceAutor;
	
	@Autowired
	private CitaService serviceCita;
	
	@Autowired
	private BookWebController webController;
	
	@RequestMapping("/obra/guardada") //Esto va en BookWebController Cuando se pulsa el boton de new
	public String añadirObra(Model model, Obra obra) {
		service.save(obra);
		
		return webController.showBooks(model); 
	}
	
	@RequestMapping("/obra/new")
	public String irObra(Model model) {
		
		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", service.findAll());
		model.addAttribute("autores", serviceAutor.findAll());
		
		return "obra";
	}
	@GetMapping ("/obra/{id}")
	public String abrirObra(Model model, @PathVariable long id) {
		
		Optional<Obra> obra= service.findOne(id);
		
		model.addAttribute("autores", serviceAutor.findAll());
		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("citas", serviceCita.findAll());
		
		if(obra.isPresent()) {
			
			model.addAttribute("title", obra.get().getTitle());
			model.addAttribute("URL", obra.get().getURL());
			model.addAttribute("date", obra.get().getDate());
			model.addAttribute("editorial", obra.get().getEditorial());
			model.addAttribute("url_editorial", obra.get().getUrl_editorial());
			return "obraShow"; 
		}else {
			return "obraShowError"; 
		}		
	}
}
