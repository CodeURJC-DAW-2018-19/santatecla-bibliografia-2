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
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.user.Tabs;
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
	private CitaService serviceCita;

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
	
	private void userTabs(Model model) {
		if (this.userComponent.isLoggedUser()) {
			Tabs tab = new Tabs("https://localhost:8443/autor/1/", false);
			this.userComponent.getLoggedUser().addTab(tab);
			this.userComponent.getLoggedUser().addTab(tab);
			System.out.println();
			System.out.println(" tamano tabs" + this.userComponent.getLoggedUser().getTabs().size());
			System.out.println();
			model.addAttribute("tabs", this.userComponent.getLoggedUser().getTabs());
		}
	}

	@GetMapping("/")
	public String showBooks(Model model) {

		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("autores", serviceAutor.findAll());
		
		//AQUI HACER LO DE REQUEST PARAM, LO HE LLAMADO authorName A LO QUE HAY QUE PASARLE
		System.out.println("fdsfs");
		userTabs(model);
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

	@GetMapping("/autor/show")
	public String showBook(Model model, String nombreAutor) {
		
		Optional<Autor> autor = serviceAutor.findOneByNombre(nombreAutor);

		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("citas", serviceCita.findAll());
		
		addUserToModel(model);
		
		if(autor.isPresent()) {
			
			model.addAttribute("nombreAutor", autor.get().getNombre());
			model.addAttribute("urlFotoAutor", autor.get().getUrl_foto());
			model.addAttribute("nacimientoAutor", autor.get().getFecha_nac());
			model.addAttribute("muerteAutor", autor.get().getFecha_def());
			model.addAttribute("urlMapa", autor.get().getUrl_mapa());		
			model.addAttribute("lugarAutor", autor.get().getLugar());
			return "autor"; 
		}else {
			return "autorError"; 
		}		
	}
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
