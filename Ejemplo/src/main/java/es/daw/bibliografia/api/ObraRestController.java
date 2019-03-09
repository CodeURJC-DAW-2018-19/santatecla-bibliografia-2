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

import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;

@RestController
public class ObraRestController {
	
	@Autowired
	private ObraService serviceObra;
	
	@Autowired
	private CitaService serviceCita;
	
	@GetMapping("/api/obra") 
	public ResponseEntity<Obra> openObra( @RequestParam("nombreObra") String nombreObra) {
		
		Optional<Obra> obra = serviceObra.findOneByTitle(nombreObra);

		if (obra.isPresent()) {
			Obra obraShow = obra.get();
			return new ResponseEntity<Obra>(obraShow, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/api/obra")
	@ResponseStatus(HttpStatus.CREATED)
	public Obra addObra(@RequestBody Obra obra) {
		serviceObra.save(obra);
		return obra;
	}
	
	@PutMapping(value="/api/obra")
	public ResponseEntity<Obra> editObra(@RequestBody Obra obra) {
		Optional<Obra> obra2 = serviceObra.findOneByTitle(obra.getTitle());
		
		if (obra2.isPresent()) {
			long id= obra2.get().getId();
			obra2.get().setDate(obra.getDate());
			obra2.get().setEditorial(obra.getEditorial());
			obra2.get().setURL(obra.getURL());
			obra2.get().setUrl_editorial(obra.getUrl_editorial());

			serviceObra.save(obra2.get());
			Obra obraEdit =serviceObra.findOne(id).get();
			return new ResponseEntity<>(obraEdit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}
	
	@DeleteMapping(value="/api/obra")
	public ResponseEntity<Obra> deleteObra(Obra obra) {
		Obra deletedObra = obra;
		serviceObra.deleteObra(obra);
		return new ResponseEntity<>(deletedObra, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/obra/{nombreObra}/borrar/autor")
	public ResponseEntity<Obra> deleteObraInAutor( @PathVariable("nombreObra") String nombreObra,
			@RequestParam("nombreAutor") String autor) {
		Obra deletedObra = serviceObra.findOneByTitle(nombreObra).get();
		serviceObra.delete(serviceObra.findOneByTitle(nombreObra).get().getId());
		return new ResponseEntity<>(deletedObra, HttpStatus.OK);
	}
	

	@DeleteMapping("/api/obra/{nombreObra}/borrar/tema")
	public ResponseEntity<Obra> deleteObraInTema(@PathVariable("nombreObra") String nombreObra,
			@RequestParam("nombreTema") String autor) {
		Obra deletedObra =serviceObra.findOneByTitle(nombreObra).get();
		serviceObra.delete(serviceObra.findOneByTitle(nombreObra).get().getId());
		return new ResponseEntity<>(deletedObra, HttpStatus.OK);
	}
	
	@PostMapping("/api/obra/{nombreObra}/cita")
	@ResponseStatus(HttpStatus.CREATED)
	public Cita createCita(@RequestBody Cita cita, @PathVariable("nombreObra") String nombreObra) {
		
		serviceObra.findOneByTitle(nombreObra).get().getCitas().add(cita);
		serviceCita.save(cita);
		
		return cita;
	}
	
	

	

}
