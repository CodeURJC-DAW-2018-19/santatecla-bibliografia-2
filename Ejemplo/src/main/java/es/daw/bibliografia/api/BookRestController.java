package es.daw.bibliografia.api;

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

import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.user.User;
import es.daw.bibliografia.user.UserComponent;
import es.daw.bibliografia.user.UserService;

@RestController
public class BookRestController {

	@Autowired
	private TemaService serviceTema;

	@Autowired
	private ObraService serviceObra;

	@Autowired
	private AutorService serviceAutor;

	@Autowired
	private CitaService serviceCita;

	@Autowired
	private UserService serviceUser;

	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping(value = "/api/signupOk", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User signup(User user, @RequestParam("pass") String pass) {
		User aux = new User(user.getName(), user.getEmail(), pass, "ROLE_USER");
		serviceUser.save(aux);
		return aux;
	}
	
}
