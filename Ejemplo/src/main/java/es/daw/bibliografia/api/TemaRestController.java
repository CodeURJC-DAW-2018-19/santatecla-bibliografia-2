package es.daw.bibliografia.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.book.Tema.Basic;


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
	
	interface TemaDetail extends Tema.Basic, Tema.Obras, Obra.Basic{}
	interface AuthorDetail extends Autor.Basic, Obra.Basic{}
	interface ObraDetail extends Obra.Basic, Obra.Authors, Autor.Basic, Obra.Quotes, Cita.Basic{}
	
	
	@GetMapping("/api/temas/")
	public Collection<Tema> getTemas() {
		return temaService.findAll();
	}
	
	@JsonView(TemaDetail.class)
	@GetMapping("/api/temas/{contenido}")
	public ResponseEntity<Tema> openTema(@PathVariable String contenido) {

		Optional<Tema> temaOpt = temaService.findOneByContenido(contenido);
		
		if (temaOpt.isPresent()) {
			return new ResponseEntity<Tema>(temaOpt.get(), HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/api/temas")
	@ResponseStatus(HttpStatus.CREATED)
	public Tema addTema(@RequestBody Tema tema) {
		tema.setObra(new ArrayList<>());
		//obraService.save(obra.get());
		temaService.save(tema);
		return tema;
	}
	
	@DeleteMapping("/api/temas/{contenido}")
	public ResponseEntity<Tema> deleteTema(@PathVariable String contenido) {
		
		Optional<Tema> deletedTema = temaService.deleteByContenido(contenido);
		deletedTema.get().setObra(new ArrayList<Obra>());
		
		if (deletedTema.isPresent()) {
			return new ResponseEntity<Tema>(deletedTema.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
		
	@JsonView(Cita.Basic.class)
	@GetMapping("/api/temas/{contenido}/citas/{id}")
	public ResponseEntity<List<Cita>> accessCitas(@PathVariable String contenido, @PathVariable Long id) {
		
		List<Cita> citas = citaService.findCitasByTema(temaService.findOne(id).get());
		
		if (citas !=null) {
			return new ResponseEntity<List<Cita>>(citas, HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	@DeleteMapping("/api/temas/{contenido}/citas/{id}")
	public ResponseEntity<Cita> deleteCita(@PathVariable String contenido, @PathVariable Long id) {
		Optional<Cita> deletedCita = citaService.findOne(id);
		
		if (deletedCita.isPresent()) {
			citaService.delete(id);
			
			return new ResponseEntity<Cita>(deletedCita.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@JsonView(AuthorDetail.class)
	@GetMapping("/api/temas/{contenido}/autores")
	public ResponseEntity<List<Autor>> showAutoresInTema(@PathVariable String contenido) {
		
		Tema tema= temaService.findOneByContenido(contenido).get();
		List<Autor> autores = autorService.findAutoresByTema(tema);
		
		if (autores!=null)
			return new ResponseEntity<List<Autor>>(autores, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
	
	@JsonView(ObraDetail.class)
	@GetMapping("/api/temas/{contenido}/obras")
	public ResponseEntity<List<Obra>> showObrasInTema(@PathVariable String contenido) {
		Tema tema = temaService.findOneByContenido(contenido).get();
		List<Obra> obras = obraService.findObrasByTema(tema);
		
		if (obras!=null)
			return new ResponseEntity<List<Obra>>(obras, HttpStatus.OK);
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
