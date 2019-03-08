package es.daw.bibliografia.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	@GetMapping("/api/autor")
	public ResponseEntity<Autor> accessAutor(@RequestParam String nombre) {
		Optional<Autor> autor = autorService.findOneByNombre(nombre);
		
		if (autor.isPresent())
			return new ResponseEntity<Autor>(autor.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@DeleteMapping("/api/autor")
	public ResponseEntity<Autor> deleteAutor(@RequestParam String nombre) {
		Optional<Autor> autor = autorService.findOneByNombre(nombre);
		
		if (autor.isPresent()) {
			autorService.deleteByNombre(nombre);
			
			return new ResponseEntity<Autor>(autor.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	
	@PostMapping("/api/autor")
	public ResponseEntity<Autor> createAutor(@RequestParam String nombre, @RequestParam String url_foto, 
			@RequestParam String fecha_nac, @RequestParam String fecha_fall, 
			@RequestParam String url_mapa, @RequestParam String lugar) {
		Autor autor = new Autor(nombre, url_foto, fecha_nac, fecha_fall, url_mapa, lugar);
		autorService.save(autor);
		
		Optional<Autor> autorOpt = autorService.findOneByNombre(nombre);
		
		if (autorOpt.isPresent()) {
			return new ResponseEntity<Autor>(autorOpt.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@DeleteMapping("/api/autor/cita")
	public ResponseEntity<Cita> deleteAutor(@RequestParam Long id) {
		Optional<Cita> deletedCita = citaService.findOne(id);
		
		if (deletedCita.isPresent()) {
			citaService.delete(id);
			
			return new ResponseEntity<Cita>(deletedCita.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
