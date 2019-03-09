package es.daw.bibliografia.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.daw.bibliografia.api.AutorRestController.AuthorDetail;
import es.daw.bibliografia.api.TemaRestController.TemaDetail;
import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.user.User;
import es.daw.bibliografia.user.UserComponent;
import es.daw.bibliografia.user.UserService;

@RestController
public class BookRestController {

	@Autowired
	private TemaService temaService;

	@Autowired
	private ObraService obraService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private CitaService citaService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserComponent userComponent;
	
	interface ObraDetail extends Obra.Basic, Obra.Authors, Autor.Basic, Obra.Quotes, Cita.Basic{}
	interface AuthorDetail extends Autor.Basic, Obra.Basic{}

	
	@RequestMapping(value = "/api/signupOk", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User signup(User user, @RequestParam("pass") String pass) {
		User aux = new User(user.getName(), user.getEmail(), pass, "ROLE_USER");
		userService.save(aux);
		return aux;
	}
	
	@JsonView(ObraDetail.class)
	@GetMapping("/api/obras")
	public Page<Obra> showObras(@RequestParam int obraPage){
		Page<Obra> obras = obraService.findAll(new PageRequest(obraPage,10));
		return obras;
	}
	
	@JsonView(AuthorDetail.class)
	@GetMapping("/api/autores")
	public Page<Autor> showAutores(@RequestParam int autorPage){
		Page<Autor> autores = autorService.findAll(new PageRequest(autorPage,10));
		return autores;
	}
	
	@JsonView(TemaDetail.class)
	@GetMapping("/api/temas")
	public Page<Tema> showTemas(@RequestParam int temaPage){
		Page<Tema> temas = temaService.findAll(new PageRequest(temaPage,10));
		return temas;
	}
	
}
