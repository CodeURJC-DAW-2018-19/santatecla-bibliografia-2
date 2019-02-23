package es.daw.bibliografia.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	private void userTabs(Model model, String url, String name, boolean active) {
		Tabs tab = new Tabs(url, name, active);
		
		if (!sameTab(tab)) {
			updateActiveTabs(active);
			if (this.userComponent.isLoggedUser()) {
				this.userComponent.getLoggedUser().addTab(tab);
				model.addAttribute("tabs", this.userComponent.getLoggedUser().getTabs());
			}
		}
	}
	
	public void deleteTab(String url) {
		this.userComponent.getLoggedUser().deleteTabByUrl(url);
	}
	
	public void updateActiveTabs(boolean active) {
		if (active==true) {
			this.userComponent.getLoggedUser().inactiveAllTabs();
		}
	}

	public boolean sameTab(Tabs tab) {
		for (int i = 0; i < this.userComponent.getLoggedUser().getTabs().size(); i++) {
			if (this.userComponent.getLoggedUser().getTabs().get(i).getName().equalsIgnoreCase(tab.getName())
					&& this.userComponent.getLoggedUser().getTabs().get(i).getUrl().equalsIgnoreCase(tab.getUrl())) {
				return true;
			}
		}
		return false;
	}

	@GetMapping("/")
	public String showBooks(Model model) {

		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("autores", serviceAutor.findAll());
		
		model.addAttribute("start", true);

		// AQUI HACER LO DE REQUEST PARAM, LO HE LLAMADO authorName A LO QUE HAY QUE
		// PASARLE

		Optional<Obra> o = serviceObra.findOneByTitle("Hamlet");
		if (o.isPresent()) {
			System.out.println(o.get().getTitle());
		}
		Optional<Tema> t = serviceTema.findOneByContenido("Tema Hamlet");
		if (t.isPresent()) {
			System.out.println(t.get().getContenido());
		}
		// model.addAttribute("autorSearch", a1.get().getNombre());

		return "Index";
	}

	@RequestMapping(value = "/autor/{nombreAutor}")
	public String showBook(Model model, @PathVariable("nombreAutor") String nombreAutor) {
		
		userTabs(model, "/autor/" + nombreAutor, "Autor " + nombreAutor, true);
		
		Optional<Autor> autor = serviceAutor.findOneByNombre(nombreAutor);

		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("citas", serviceCita.findAll());

		addUserToModel(model);

		if (autor.isPresent()) {

			model.addAttribute("nombreAutor", autor.get().getNombre());
			model.addAttribute("urlFotoAutor", autor.get().getUrl_foto());
			model.addAttribute("nacimientoAutor", autor.get().getFecha_nac());
			model.addAttribute("muerteAutor", autor.get().getFecha_def());
			model.addAttribute("urlMapa", autor.get().getUrl_mapa());
			model.addAttribute("lugarAutor", autor.get().getLugar());
			return "autor";
		} else {
			return "autorError";
		}
	}
	
	@RequestMapping (value = "/obra/{nombreObra}")//PUT IN BOOKWEEBCONTROLER
	public String openObra(Model model, @PathVariable("nombreObra") String nombreObra) {
		
		userTabs(model, "/obra/" + nombreObra, "Obra  " + nombreObra, true);
		
		Optional<Obra> obra= serviceObra.findOneByTitle(nombreObra);
		
		model.addAttribute("autores", serviceAutor.findAll());
		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("citas", serviceCita.findAll());
		
		addUserToModel(model);
		
		if(obra.isPresent()) {
			
			model.addAttribute("title", obra.get().getTitle());
			model.addAttribute("URL", obra.get().getURL());
			model.addAttribute("date", obra.get().getDate());
			model.addAttribute("editorial", obra.get().getEditorial());
			model.addAttribute("url_editorial", obra.get().getUrl_editorial());
			return "obraShow"; 
		}else {
			return "obraShowError"; 
		}		
	}
	
	@RequestMapping(value = "/obra/new", method = RequestMethod.POST)
	public String goObra(Model model) {
		//userTabs(model, "/obra/new", "Nueva obra", true);
		
		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("autores", serviceAutor.findAll());
		
		addUserToModel(model);
		
		return "obra";
	}
	
	@RequestMapping("/delete")
	private String closeTabs(Model model) {
//		System.out.println("dfsfs");
//		deleteTab(url);
//		System.out.println("dfsfs");
		
		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("autores", serviceAutor.findAll());
		
		addUserToModel(model);
		return "redirect:/";
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
