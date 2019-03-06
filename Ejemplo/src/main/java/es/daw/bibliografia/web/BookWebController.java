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
