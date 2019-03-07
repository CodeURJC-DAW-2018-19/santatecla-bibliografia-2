package es.daw.bibliografia.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;


@RestController
public class TemaRestController {
	
	@Autowired
	private TemaService temaService;
	
	@Autowired
	private CitaService citaService;
	
	
	
	@DeleteMapping("/api/tema/borrarCita/{id}")
	public Cita deleteCita(@PathVariable long id) {

		Cita deletedCita= citaService.findOne(id).get();
		citaService.delete(id);

		return deletedCita;
	}

	@DeleteMapping("/api/tema/borrar")
	public Tema deleteTema(@RequestParam String contenido) {
		
		Tema deletedTema= temaService.findOneByContenido(contenido).get();
		temaService.delete(temaService.findOneByContenido(contenido).get().getId());
		
		return deletedTema;
	}

	@PostMapping("/api/tema/guardado")
	public String addAutor(Tema tema) {
		temaService.save(tema);


		return "redirect:/temashow/".concat(tema.getContenido());
	}

	@DeleteMapping("/api/tema/{nombreTema}/borrar/autor")
	public String deleteTema2(@PathVariable("nombreTema") String nombreObra,
			@RequestParam("nombreAutor") String autor) {
		temaService.delete(temaService.findOneByContenido(nombreObra).get().getId());


		return "redirect:/autorshow/".concat(autor);
	}
	
	
}
