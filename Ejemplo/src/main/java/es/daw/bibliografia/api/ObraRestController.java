package es.daw.bibliografia.api;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import es.daw.bibliografia.book.Obra.Basic;

@RestController
public class ObraRestController {
	
	@Autowired
	private ObraService obraService;
	
	@Autowired
	private CitaService citaService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private TemaService temaService;
	
	interface ObraDetail extends Obra.Basic, Obra.Authors, Autor.Basic, Obra.Quotes, Cita.Basic{}

	
	@JsonView(ObraDetail.class)
	@GetMapping("/api/obras/{title}") 
	public ResponseEntity<Obra> openObra( @PathVariable("title") String title) {
		
		Optional<Obra> obra = obraService.findOneByTitle(title);

		if (obra.isPresent()) {
			Obra obraShow = obra.get();
			return new ResponseEntity<Obra>(obraShow, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/api/obras")
	public ResponseEntity<Obra> addObra(@RequestBody Obra obra,@RequestParam long idAutor, @RequestParam long idTema) {
		Optional<Tema> tema = temaService.findOne(idTema);
		if(tema.isPresent()) {
			obra.setAutores(new ArrayList<>());
			obra.setCitas(new ArrayList<>());
			Optional<Autor> autor = autorService.findOne(idAutor);
			if(autor.isPresent()) {
				obra.getAutores().add(autor.get());
			}
			tema.get().getObras().add(obra);
			tema.get().setNumObras(tema.get().getNumObras()+1);
			obraService.save(obra);
			return new ResponseEntity<>(obra, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@PutMapping("/api/obras")
	public ResponseEntity<Obra> editObra(@RequestBody Obra obra) {
		Optional<Obra> obra2 = obraService.findOneByTitle(obra.getTitle());
		
		if (obra2.isPresent()) {
			obra2.get().setDate(obra.getDate());
			obra2.get().setEditorial(obra.getEditorial());
			obra2.get().setURL(obra.getURL());
			obra2.get().setUrl_editorial(obra.getUrl_editorial());

			obraService.save(obra2.get());
			return new ResponseEntity<>(obra2.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

		}
	}
	
	@PutMapping("/api/obras/autor")
	public ResponseEntity<Obra> editObraLinkAutor(@RequestParam long idObra, @RequestParam long idAutor) {
		Optional<Obra> obra = obraService.findOne(idObra);
		
		if (obra.isPresent()) {
			Optional<Autor> autor = autorService.findOne(idAutor);
			if(autor.isPresent()) {
				obra.get().getAutores().add(autor.get());
			}
			obraService.save(obra.get());
			return new ResponseEntity<>(obra.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

		}
	}
	
	@PutMapping("/api/obras/cita")
	public ResponseEntity<Obra> addCita(@RequestParam long idObra, @RequestParam String contenido) {
		Optional<Obra> obra = obraService.findOne(idObra);
		
		if (obra.isPresent()) {
			Cita cita = new Cita(contenido);
			obra.get().getCitas().add(cita);
			citaService.save(cita);
			return new ResponseEntity<>(obra.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		}
	}
	
	@DeleteMapping("/api/obras/{title}")
	public ResponseEntity<Obra> deleteObra(@PathVariable String title) {
		Optional<Obra> obra = obraService.findOneByTitle(title);
		if (obra.isPresent()) {
			obraService.deleteObra(obra.get());
			obra.get().setAutores(new ArrayList<>());
			obra.get().setCitas(new ArrayList<>());
			return new ResponseEntity<Obra>(obra.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/api/obra/{nombreObra}/borrar/autor")
	public ResponseEntity<Obra> deleteObraInAutor( @PathVariable("nombreObra") String nombreObra,
			@RequestParam("nombreAutor") String autor) {
		Obra deletedObra = obraService.findOneByTitle(nombreObra).get();
		obraService.delete(obraService.findOneByTitle(nombreObra).get().getId());
		return new ResponseEntity<>(deletedObra, HttpStatus.OK);
	}
	

	@DeleteMapping("/api/obra/{nombreObra}/borrar/tema")
	public ResponseEntity<Obra> deleteObraInTema(@PathVariable("nombreObra") String nombreObra,
			@RequestParam("nombreTema") String autor) {
		Obra deletedObra =obraService.findOneByTitle(nombreObra).get();
		obraService.delete(obraService.findOneByTitle(nombreObra).get().getId());
		return new ResponseEntity<>(deletedObra, HttpStatus.OK);
	}
	
	

	

}
