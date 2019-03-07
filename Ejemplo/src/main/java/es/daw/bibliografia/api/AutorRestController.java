package es.daw.bibliografia.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Tema;

@RestController
public class AutorRestController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private CitaService citaService;
	
	@PostMapping("/api/autor/guardado")
	public String addAutor(Autor autor) {
		autorService.save(autor);


		return "redirect:/autorshow/".concat(autor.getNombre());
	}
	
	@DeleteMapping("/api/autor/borrar")
	public Autor deleteTema(@RequestParam String nombre) {
		
		Autor deletedAutor= autorService.findOneByNombre(nombre).get();
		autorService.delete(autorService.findOneByNombre(nombre).get().getId());
		
		return deletedAutor;
	}
	
	@DeleteMapping("/api/autor/borrarCita/{id}")
	public Cita deleteCita(@PathVariable long id) {

		Cita deletedCita= citaService.findOne(id).get();
		citaService.delete(id);

		return deletedCita;
	}
}
