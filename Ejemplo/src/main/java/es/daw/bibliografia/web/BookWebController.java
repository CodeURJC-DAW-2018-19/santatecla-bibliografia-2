package es.daw.bibliografia.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Book;
import es.daw.bibliografia.book.BookService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.user.UserComponent;

@Controller
public class BookWebController {

	@Autowired
	private TemaService serviceTema;

	@Autowired
	private ObraService serviceObra;

	@Autowired
	private AutorService serviceAutor;

	@Autowired
	private UserComponent userComponent;

	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if (logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			model.addAttribute("userName", userComponent.getLoggedUser().getName());
		}
	}

	@GetMapping("/")
	public String showBooks(Model model) {

		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("autores", serviceAutor.findAll());
		
		//AQUI HACER LO DE REQUEST PARAM, LO HE LLAMADO authorName A LO QUE HAY QUE PASARLE

		Optional<Obra> o = serviceObra.findOneByTitle("Hamlet");
		if (o.isPresent()) {
			System.out.println(o.get().getTitle());
		}
		Optional<Tema> t = serviceTema.findOneByContenido("Tema Hamlet");
		if (t.isPresent()) {
			System.out.println(t.get().getContenido());
		}
		//model.addAttribute("autorSearch", a1.get().getNombre());
		
		return "Index";
	}

//	@GetMapping("/books/{id}")
//	public String showBook(Model model, @PathVariable long id) {
//		
//		Optional<Book> book = service.findOne(id);
//
//		if(book.isPresent()) {
//			model.addAttribute("book", book.get());
//		}
//
//		return "book";
//	}
//	
//	@GetMapping("/newBook")
//	public String newBook(Model model) {
//		return "bookForm";
//	}
//	
//	@GetMapping("/editBook/{id}")
//	public String newBook(Model model, @PathVariable long id) {
//		
//		Optional<Book> book = service.findOne(id);
//		
//		if(book.isPresent()) {
//			model.addAttribute("book", book.get());
//		}
//		
//		return "bookForm";
//	}
//	
//	@PostMapping("/saveBook")
//	public String saveBook(Model model, Book book) {
//		
//		service.save(book);
//		
//		return "bookCreated";
//	}
//	
//	@GetMapping("/deleteBook/{id}")
//	public String deleteBook(Model model, @PathVariable long id) {
//		
//		service.delete(id);
//		
//		return "bookDeleted";
//	}
//	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("hideLogin", true);
		return "login";
	}

	@GetMapping("/loginerror")
	public String loginError() {
		return "loginerror";
	}
}
