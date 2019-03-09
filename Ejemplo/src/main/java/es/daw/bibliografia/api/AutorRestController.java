package es.daw.bibliografia.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;

@RestController
public class AutorRestController {

	@Autowired
	private AutorService autorService;

	@Autowired
	private CitaService citaService;
	
	@Autowired
	private TemaService temaService;
	
	@Autowired
	private ObraService obraService;
	
	
	@GetMapping("/api/autores/{nombre}")
	public ResponseEntity<Autor> showAutor(@PathVariable String nombre) {
		Optional<Autor> autor = autorService.findOneByNombre(nombre);
		
		if (autor.isPresent())
			return new ResponseEntity<Autor>(autor.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@PostMapping("/api/autores")
	public ResponseEntity<Autor> createAutor(@RequestBody Autor autor){
		autorService.save(autor);
		
		Optional<Autor> autorOpt = autorService.findOneByNombre(autor.getNombre());
		
		if (autorOpt.isPresent()) {
			return new ResponseEntity<Autor>(autorOpt.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping("/api/autores/{nombre}")
	public ResponseEntity<Autor> deleteAutor(@PathVariable String nombre) {
		Optional<Autor> autor = autorService.findOneByNombre(nombre);
		
		if (autor.isPresent()) {
			autorService.deleteByNombre(nombre);
			
			return new ResponseEntity<Autor>(autor.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	

	@GetMapping("/api/autores/{nombre}/temas/{contenido}")
	public ResponseEntity<Tema> showTemaInAutor(@PathVariable String nombre, @PathVariable String contenido) {
		Optional<Tema> tema = temaService.findOneByContenido(contenido);
		
		if (tema.isPresent()) {
			
			return new ResponseEntity<Tema>(tema.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@DeleteMapping("/api/autores/{nombre}/temas/{contenido}")
	public ResponseEntity<Tema> deleteTemaInAutor(@PathVariable String nombre, @PathVariable String contenido) {
		Optional<Tema> deletedTema = temaService.findOneByContenido(contenido);
		
		if (deletedTema.isPresent()) {
			temaService.deleteByContenido(contenido);
			
			return new ResponseEntity<Tema>(deletedTema.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/api/autores/{nombre}/obras/{title}")
	public ResponseEntity<Obra> showObraInAutor(@PathVariable String nombre, @PathVariable String title) {
		Optional<Obra> obra = obraService.findOneByTitle(title);
		
		if (obra.isPresent()) {
			
			return new ResponseEntity<Obra>(obra.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@DeleteMapping("/api/autores/{nombre}/obras/{title}")
	public ResponseEntity<Obra> deleteObraInAutor(@PathVariable String nombre, @PathVariable String title) {
		Optional<Obra> deletedObra = obraService.findOneByTitle(title);
		if (deletedObra.isPresent()) {
			obraService.deleteByTitle(title);
			
			return new ResponseEntity<Obra>(deletedObra.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@GetMapping("/api/autores/{nombre}/citas/{id}")
	public ResponseEntity<Cita> showCitaInAutor(@PathVariable String nombre, @PathVariable Long id) {
		Optional<Cita> cita = citaService.findOne(id);
		
		if (cita.isPresent()) {
			
			return new ResponseEntity<Cita>(cita.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@DeleteMapping("/api/autores/{nombre}/citas/{id}")
	public ResponseEntity<Cita> deleteCitaInAutor(@PathVariable String nombre, @PathVariable Long id) {
		Optional<Cita> deletedCita = citaService.findOne(id);
		
		if (deletedCita.isPresent()) {
			citaService.delete(id);
			
			return new ResponseEntity<Cita>(deletedCita.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	
}
