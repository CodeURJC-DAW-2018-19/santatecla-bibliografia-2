package es.daw.bibliografia.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.Obra.Basic;

@RestController
public class ObraRestController {
	
	@Autowired
	private ObraService obraService;
	
	@Autowired
	private CitaService citaService;
	
	@JsonView(Obra.Basic.class)
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
	@ResponseStatus(HttpStatus.CREATED)
	public Obra addObra(@RequestBody Obra obra) {
		obraService.save(obra);
		return obra;
	}
	
	@PutMapping("/api/obras/{title}")
	public ResponseEntity<Obra> editObra(@RequestBody Obra obra, @PathVariable String title) {
		Optional<Obra> obra2 = obraService.findOneByTitle(title);
		
		if (obra2.isPresent()) {
			long id= obra2.get().getId();
			obra2.get().setDate(obra.getDate());
			obra2.get().setEditorial(obra.getEditorial());
			obra2.get().setURL(obra.getURL());
			obra2.get().setUrl_editorial(obra.getUrl_editorial());

			obraService.save(obra2.get());
			Obra obraEdit =obraService.findOne(id).get();
			return new ResponseEntity<>(obraEdit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}
	
	@DeleteMapping("/api/obras/{title}")
	public ResponseEntity<Obra> deleteObra(@PathVariable String title) {
		Optional<Obra> obraOpt = obraService.findOneByTitle(title);
		if (obraOpt.isPresent()) {
			Obra deletedObra = obraOpt.get();
			obraService.deleteObra(obraOpt.get());
			
			return new ResponseEntity<Obra>(deletedObra, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
	
	@PostMapping("/api/obra/{nombreObra}/cita")
	@ResponseStatus(HttpStatus.CREATED)
	public Cita createCita(@RequestBody Cita cita, @PathVariable("nombreObra") String nombreObra) {
		
		obraService.findOneByTitle(nombreObra).get().getCitas().add(cita);
		citaService.save(cita);
		
		return cita;
	}
	
	

	

}
