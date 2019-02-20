package es.daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.BookService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.user.UserComponent;

@Controller
public class AutorWebController {

	@Autowired
	private ObraService obraService;

	@Autowired
	private TemaService temaService;

	@Autowired
	private CitaService citaService;
	
	@Autowired
	private AutorService autorService;
	
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private BookWebController webController;

	
	/*
	 * @RequestMapping("/autor") public String showAutor(Model model) {
	 * model.addAttribute("obras", obraService.findAll());
	 * model.addAttribute("temas", temaService.findAll());
	 * model.addAttribute("citas", citaService.findAll()); return "autor"; }
	 */
	
	@GetMapping("/autor/{id}")
	public String showBook(Model model, @PathVariable long id) {
		Optional<Autor> autor = autorService.findOne(id);
		
		model.addAttribute("obras", obraService.findAll());
		model.addAttribute("temas", temaService.findAll());
		model.addAttribute("citas", citaService.findAll());
		
		webController.addUserToModel(model);
		
		if(autor.isPresent()) {
			List<Obra> obras = obraService.findByAuthor(autor.get());
			System.out.println(obras.size());
			
			model.addAttribute("nombreAutor", autor.get().getNombre());
			model.addAttribute("urlFotoAutor", autor.get().getUrl_foto());
			model.addAttribute("nacimientoAutor", autor.get().getFecha_nac());
			model.addAttribute("muerteAutor", autor.get().getFecha_def());
			model.addAttribute("urlMapa", autor.get().getUrl_mapa());		
			model.addAttribute("lugarAutor", autor.get().getLugar());
			
			return "autor";
		}
		else
			return "autorError";

	}
}
