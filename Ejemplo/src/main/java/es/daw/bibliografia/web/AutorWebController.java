package es.daw.bibliografia.web;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
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
		webController.addUserToModel(model);
		
		/*
		 * Optional<Autor> a1 = autorService.findOneByNombre("William Shakespeare"); if
		 * (a1.isPresent()) { System.out.println(a1.get().getNombre()); }
		 */
		/*
		 * Optional<Obra> o = obraService.findOneByTitle("Hamlet"); if (o.isPresent()) {
		 * System.out.println(o.get().getTitle()); } Optional<Cita> c =
		 * citaService.findOneByContenido("Cita Hamlet"); if (c.isPresent()) {
		 * System.out.println(c.get().getContenido()); } Optional<Tema> t =
		 * temaService.findOneByContenido("Tema Hamlet"); if (t.isPresent()) {
		 * System.out.println(t.get().getContenido()); }
		 */
		
		if(autor.isPresent()) {
			List<Obra> obras = obraService.findByAuthor(autor.get());
			List<Tema> temas = new ArrayList<>();
			List<Cita> citas = new ArrayList<>();
			for(int i=0; i<obras.size(); i++) {
				temas.add(temaService.findByObra(obras.get(i)));
				citas = Stream.concat(citas.stream(), obras.get(i).getCitas().stream())
                        .collect(Collectors.toList());
				//temaService.findByObra(obras.get(i))
				//System.out.println(obras.get(i).getTitle());
			}
			model.addAttribute("obras", obras);
			model.addAttribute("temas", temas);
			model.addAttribute("citas", citas);
			
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
