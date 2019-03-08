package es.daw.bibliografia.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;


@RestController
public class TemaRestController {
	
	@Autowired
	private TemaService temaService;
	
	@Autowired
	private CitaService citaService;
	
	@Autowired
	private AutorService autorService;
	
	@Autowired
	private ObraService obraService;
	
	@GetMapping("/api/tema")
	public ResponseEntity<Tema> accessTema(@RequestParam Long id) {

		Optional<Tema> temaOpt = temaService.findOne(id);
		
		if (temaOpt.isPresent()) {
			return new ResponseEntity<Tema>(temaOpt.get(), HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/api/tema")
	public ResponseEntity<Tema> addTema(@RequestParam String contenido) {
		Tema tema= new Tema();
		tema.setContenido(contenido);
		temaService.save(tema);
		
		Optional<Tema> createdTema = temaService.findOneByContenido(contenido);
		
		if (createdTema!=null) {
			return new ResponseEntity<Tema>(tema,HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@DeleteMapping("/api/tema")
	public ResponseEntity<Tema> deleteTema(@RequestParam String contenido) {
		
		Optional<Tema> deletedTema = temaService.deleteByContenido(contenido);
		
		if (deletedTema.isPresent()) {
			return new ResponseEntity<Tema>(deletedTema.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
			
	@GetMapping("/api/tema/cita")
	public ResponseEntity<Cita> accessCita(@RequestParam Long id) {

		Optional<Cita> cita = citaService.findOne(id);
		
		if (cita.isPresent()) {
			return new ResponseEntity<Cita>(cita.get(), HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	@DeleteMapping("/api/tema/cita")
	public ResponseEntity<Cita> deleteAutor(@RequestParam Long id) {
		Optional<Cita> deletedCita = citaService.findOne(id);
		
		if (deletedCita.isPresent()) {
			citaService.delete(id);
			
			return new ResponseEntity<Cita>(deletedCita.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@GetMapping("/api/tema/autor")
	public ResponseEntity<Autor> accessAutor(@RequestParam String nombre) {
		Optional<Autor> autor = autorService.findOneByNombre(nombre);
		
		if (autor.isPresent())
			return new ResponseEntity<Autor>(autor.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@GetMapping("/api/tema/obra")
	public ResponseEntity<Obra> accessObra(@RequestParam Long id) {
		Optional<Obra> obra = obraService.findOne(id);
		
		if (obra.isPresent())
			return new ResponseEntity<Obra>(obra.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	//Method not used (You can't delete Autor in Tema pages)
/*
	@DeleteMapping("/api/tema/autor")
	public ResponseEntity<Autor> deleteAutor(@RequestParam String nombre) {
		Optional<Autor> autor = autorService.findOneByNombre(nombre);
		
		if (autor.isPresent()) {
			autorService.deleteByNombre(nombre);
			
			return new ResponseEntity<Autor>(autor.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
*/
	
}
