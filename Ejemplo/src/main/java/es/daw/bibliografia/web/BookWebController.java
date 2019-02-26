package es.daw.bibliografia.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.daw.bibliografia.book.Autor;
import es.daw.bibliografia.book.AutorService;
import es.daw.bibliografia.book.Cita;
import es.daw.bibliografia.book.CitaService;
import es.daw.bibliografia.book.Obra;
import es.daw.bibliografia.book.ObraService;
import es.daw.bibliografia.book.Tema;
import es.daw.bibliografia.book.TemaService;
import es.daw.bibliografia.pdfgenerator.CreatePDF;
import es.daw.bibliografia.user.Tabs;
import es.daw.bibliografia.user.User;
import es.daw.bibliografia.user.UserComponent;
import es.daw.bibliografia.user.UserService;

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
	private UserService serviceUser;

	@Autowired
	private UserComponent userComponent;

	@Autowired
	private CreatePDF createPDF;

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
			}
		}
		modelTabs(model);
	}

	public void modelTabs(Model model) {
		try {
			if (!this.userComponent.getLoggedUser().getTabs().isEmpty())
				model.addAttribute("tabs", this.userComponent.getLoggedUser().getTabs());
		} catch (Exception e) {

		}
	}

	public void deleteTab(String name) {
		this.userComponent.getLoggedUser().deleteTabByName(name);
		for (int i = 0; i < this.userComponent.getLoggedUser().getTabs().size(); i++) {
			System.out.println(this.userComponent.getLoggedUser().getTabs().get(i).getName());
		}

	}

	public void updateActiveTabs(boolean active) {
		if (active == true) {
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

	@GetMapping("/table-theme")
	public String showMoreTheme(Model model, Pageable page) {
		Page<Tema> themes = serviceTema.findAll(page);

		model.addAttribute("temas", themes);
		model.addAttribute("nTheme", page.getPageNumber());
		model.addAttribute("indexTheme", themes.getTotalPages());

		return "pageableTema";
	}

	@GetMapping("/table-works")
	public String showMoreWorks(Model model, Pageable page) {
		Page<Obra> works = serviceObra.findAll(page);

		model.addAttribute("works", works);
		model.addAttribute("nWorks", page.getPageNumber());
		model.addAttribute("indexWorks", works.getTotalPages());

		return "pageableObra";
	}

	@GetMapping("/table-author")
	public String showMoreAuthor(Model model, Pageable page) {
		Page<Autor> author = serviceAutor.findAll(page);

		model.addAttribute("author", author);
		model.addAttribute("nAuthor", page.getPageNumber());
		model.addAttribute("indexAuthors", author.getTotalPages());

		return "pageableAutor";
	}

	@GetMapping("/")
	public String showBooks(Model model) {
		model.addAttribute("temas", serviceTema.findAll(new PageRequest(0, 10)));
		model.addAttribute("obras", serviceObra.findAll(new PageRequest(0, 10)));
		model.addAttribute("autores", serviceAutor.findAll(new PageRequest(0, 10)));

		model.addAttribute("start", true);


		modelTabs(model);
		return "Index";
	}

	@RequestMapping(value = "/autorshow/{nombreAutor}")
	public String showBook(Model model, @PathVariable("nombreAutor") String nombreAutor) {
		Optional<Autor> autor = serviceAutor.findOneByNombre(nombreAutor);


		addUserToModel(model);

		if (autor.isPresent()) {

			userTabs(model, "/autor/" + nombreAutor, "Autor " + nombreAutor, true);

			List<Obra> obras = serviceObra.findByAuthor(autor.get());
			List<Tema> temas = new ArrayList<>();
			List<Cita> citas = new ArrayList<>();
			Tema tema;
			for (int i = 0; i < obras.size(); i++) {
				tema = serviceTema.findByObra(obras.get(i));
				if (tema != null)
					temas.add(tema);
				citas = Stream.concat(citas.stream(), obras.get(i).getCitas().stream()).collect(Collectors.toList());
				
			}
			model.addAttribute("obras", obras);
			model.addAttribute("temas", temas);
			model.addAttribute("citas", citas);

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

	@RequestMapping(value = "/obrashow/{nombreObra}") // PUT IN BOOKWEEBCONTROLER
	public String openObra(Model model, @PathVariable("nombreObra") String nombreObra) {

		Optional<Obra> obra = serviceObra.findOneByTitle(nombreObra);

		addUserToModel(model);
		//serviceCita.save(cita);
		if (obra.isPresent()) {

			userTabs(model, "/obra/" + nombreObra, "Obra  " + nombreObra, true);

			Tema tema = serviceTema.findByObra(obra.get());
			List<Cita> citas = obra.get().getCitas();
			List<Autor> autores = obra.get().getAutores();

			model.addAttribute("autores", autores);
			model.addAttribute("temas", tema);
			model.addAttribute("citas", citas);

			model.addAttribute("title", obra.get().getTitle());
			model.addAttribute("URL", obra.get().getURL());
			model.addAttribute("date", obra.get().getDate());
			model.addAttribute("editorial", obra.get().getEditorial());
			model.addAttribute("url_editorial", obra.get().getUrl_editorial());
			return "obraShow";
		} else {
			return "obraShowError";
		}
	}

	@RequestMapping("/temashow/{contenido}")
	public String accederTema(Model model, @PathVariable("contenido") String contenido) {

		Optional<Tema> tema = serviceTema.findOneByContenido(contenido);

		addUserToModel(model);

		if (tema.isPresent()) {

			userTabs(model, "/tema/" + contenido, "Tema: " + contenido, true);

			Tema theme = tema.get();
			List<Obra> obras = theme.getObras();
			List<Cita> citas = new ArrayList<>();
			List<Autor> autores = new ArrayList<>();
			for (int i = 0; i < obras.size(); i++) {
				citas = Stream.concat(citas.stream(), obras.get(i).getCitas().stream()).collect(Collectors.toList());
				autores = Stream.concat(autores.stream(), obras.get(i).getAutores().stream())
						.collect(Collectors.toList());

			}

			model.addAttribute("tema", tema);
			model.addAttribute("obras", obras);
			model.addAttribute("citas", citas);
			model.addAttribute("autores", autores);

			createPDF.generatePDF(userComponent.getLoggedUser(), obras);
			return "tema";
		} else {
			return "temaShowError";
		}
	}

	@RequestMapping(value = "/obra/new")
	public String goObra(Model model) {
		userTabs(model, "/obra/new", "Nueva obra", true);

		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("autores", serviceAutor.findAll());

		addUserToModel(model);

		return "obra";
	}

	@RequestMapping(value = "/autor/new")
	public String goAutor(Model model) {
		userTabs(model, "/obra/new", "Nuevo autor", true);

		model.addAttribute("temas", serviceTema.findAll());
		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("autores", serviceAutor.findAll());

		addUserToModel(model);

		return "autorNew";
	}

	@RequestMapping(value = "/tema/new")
	public String goTema(Model model) {
		userTabs(model, "/obra/new", "Nuevo tema", true);

		model.addAttribute("obras", serviceObra.findAll());
		model.addAttribute("autores", serviceAutor.findAll());
		model.addAttribute("citas", serviceCita.findAll());
		addUserToModel(model);

		return "temaNew";
	}

	@GetMapping("/delete/{name}")
	private String closeTabs(Model model, @PathVariable String name) {
		System.out.println("delete url");

		deleteTab(name);


		addUserToModel(model);
		return "redirect:/";
	}

	@RequestMapping(value = "/signupOk", method = RequestMethod.POST)
	public String signup(Model model, User user, @RequestParam("pass") String pass) {
		addUserToModel(model);
		User aux = new User(user.getName(), user.getEmail(), pass, "ROLE_USER");
		serviceUser.save(aux);

		return "redirect:/";
	}


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
